import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

public class CentralResgate implements Serializable {

    private String nome;
    private Map<Integer, Robo> robos;
    private List<AreaOperacao> areas;
    private List<Missao> missoes;
    private Queue<Missao> missoesPendentes;

    public CentralResgate(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da central não pode ser vazio.");
        }

        this.nome = nome;
        this.robos = new HashMap<>();
        this.areas = new ArrayList<>();
        this.missoes = new ArrayList<>();
        this.missoesPendentes = new LinkedList<>();
    }

    // 1. Cadastro de Robô
    public void cadastrarRobo(Robo robo) {
        if (robo == null) {
            throw new IllegalArgumentException("O robô não pode ser nulo.");
        }
        if (robos.containsKey(robo.getId())) {
            throw new IllegalArgumentException("Já existe um robô cadastrado com o ID " + robo.getId() + ".");
        }

        robos.put(robo.getId(), robo);
        System.out.println("Robô " + robo.getNome() + " (ID: " + robo.getId() + ") cadastrado com sucesso.");
    }

    // 2. Cadastro de Área
    public void cadastrarArea(AreaOperacao area) {
        if (area == null) {
            throw new IllegalArgumentException("A área não pode ser nula.");
        }

        for (AreaOperacao a : areas) {
            if (a.getId() == area.getId()) {
                throw new IllegalArgumentException("Já existe uma área cadastrada com o ID " + area.getId() + ".");
            }
        }

        areas.add(area);
        System.out.println("Área " + area.getNome() + " (ID: " + area.getId() + ") cadastrada com sucesso.");
    }

    // 3. Registro de Missão
    public void registrarMissao(Missao missao) {
        if (missao == null) {
            throw new IllegalArgumentException("A missão não pode ser nula.");
        }

        for (Missao m : missoes) {
            if (m.getId() == missao.getId()) {
                throw new IllegalArgumentException("Já existe uma missão com o ID " + missao.getId() + ".");
            }
        }

        missoes.add(missao);
        missoesPendentes.offer(missao); // Adiciona na fila de atendimento
        System.out.println("Missão " + missao.getId() + " registrada e adicionada à fila de pendências.");
    }

    // 4. Listar Robôs Disponíveis (usando Streams)
    public List<Robo> listarRobosDisponiveis() {
        System.out.println("\n=== Robôs Disponíveis ===");
        List<Robo> disponiveis = robos.values().stream()
                .filter(Robo::verificarDisponibilidade)
                .toList();

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum robô disponível no momento.");
        } else {
            for (Robo r : disponiveis) {
                r.exibirResumo();
                System.out.println("-------------------------");
            }
        }

        return disponiveis;
    }

    // 5. Atribuir Robô a Missão
    public void atribuirRoboMissao(int idRobo, int idMissao) {
        Robo robo = robos.get(idRobo);
        if (robo == null) {
            throw new IllegalArgumentException("Robô com ID " + idRobo + " não encontrado.");
        }

        Missao missao = buscarMissaoPorId(idMissao);
        if (missao == null) {
            throw new IllegalArgumentException("Missão com ID " + idMissao + " não encontrada.");
        }

        // Tenta atribuir o robô na missão (valida compatibilidade, bateria e
        // disponibilidade)
        missao.atribuirRobo(robo);

        // Se atribuído com sucesso, remove da fila de pendentes
        missoesPendentes.remove(missao);
    }

    // 6. Listar Missões Pendentes (da fila)
    public void listarMissoesPendentes() {
        System.out.println("\n=== Fila de Missões Pendentes ===");
        if (missoesPendentes.isEmpty()) {
            System.out.println("Não há missões pendentes na fila.");
            return;
        }

        int posicao = 1;
        for (Missao m : missoesPendentes) {
            System.out.println("Posição na fila: " + posicao++);
            m.exibirResumo();
            System.out.println("-------------------------");
        }
    }

    // 7. Gerar Relatório Geral
    // usei lambda e stream para gerar o relatorio
    public Relatorio gerarRelatorioGeral() {
        int totalRobos = robos.size();

        long disponiveis = robos.values().stream()
                .filter(Robo::verificarDisponibilidade)
                .count();

        int totalMissoes = missoes.size();

        long concluidas = missoes.stream()
                .filter(m -> m.getStatus() == StatusMissao.CONCLUIDA)
                .count();

        int pendentesFila = missoesPendentes.size();

        long canceladas = missoes.stream()
                .filter(m -> m.getStatus() == StatusMissao.CANCELADA)
                .count();

        Relatorio relatorio = new Relatorio(
                totalRobos,
                (int) disponiveis,
                totalMissoes,
                (int) concluidas,
                pendentesFila,
                (int) canceladas);

        System.out.println(relatorio.gerarResumo());
        return relatorio;
    }

    // Execução de missão
    public void executarMissao(int idMissao, String resultado) {
        Missao missao = buscarMissaoPorId(idMissao);
        if (missao == null) {
            throw new IllegalArgumentException("Missão com ID " + idMissao + " não encontrada.");
        }

        missao.iniciar();
        missao.concluir(resultado);
    }

    // FUNCIONALIDADE INOVADORA (Obrigatória - 1,0 ponto):
    // Despacho inteligente automático da próxima missão da fila
    public boolean atenderProximaMissaoAutomaticamente(String resultadoPadrao) {
        System.out.println("\n=== Despacho Inteligente Automático ===");

        if (missoesPendentes.isEmpty()) {
            System.out.println("Nenhuma missão pendente na fila para atendimento automático.");
            return false;
        }

        Missao missao = missoesPendentes.peek(); // Olha a primeira da fila sem remover

        // Procura robô disponível, compatível e com mais bateria usando Streams
        Optional<Robo> melhorRobo = robos.values().stream()
                .filter(Robo::verificarDisponibilidade)
                .filter(r -> r.podeExecutar(missao.getTipo()))
                .filter(r -> r.consultarBateria() >= r.calcularConsumo(missao))
                .max(Comparator.comparingDouble(Robo::consultarBateria));

        if (melhorRobo.isPresent()) {
            Robo robo = melhorRobo.get();
            System.out.println("Melhor robô encontrado para a missão " + missao.getId() + ": " + robo.getNome()
                    + " (Bateria: " + robo.consultarBateria() + "%)");

            // Remove da fila e atribui
            missoesPendentes.poll();
            missao.atribuirRobo(robo);

            // Inicia e conclui a missão automaticamente
            missao.iniciar();
            missao.concluir(resultadoPadrao);
            return true;
        } else {
            System.out.println("Nenhum robô compatível/disponível com bateria suficiente para a missão "
                    + missao.getId() + ". Missão continua na fila.");
            return false;
        }
    }

    // Persistência de Dados (Regra 10 do trabalho)
    public void salvarDados(String caminhoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(this);
            System.out.println("Dados da central salvos com sucesso em: " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados da central: " + e.getMessage());
        }
    }

    public static CentralResgate carregarDados(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            CentralResgate central = (CentralResgate) ois.readObject();
            System.out.println("Dados da central carregados com sucesso de: " + caminhoArquivo);
            return central;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados da central: " + e.getMessage());
            return null;
        }
    }

    // Métodos auxiliares de busca
    public Robo buscarRoboPorId(int id) {
        return robos.get(id);
    }

    public AreaOperacao buscarAreaPorId(int id) {
        for (AreaOperacao a : areas) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public Missao buscarMissaoPorId(int id) {
        for (Missao m : missoes) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    // Getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da central não pode ser vazio.");
        }
        this.nome = nome;
    }

    public Map<Integer, Robo> getRobos() {
        return robos;
    }

    public List<AreaOperacao> getAreas() {
        return areas;
    }

    public List<Missao> getMissoes() {
        return missoes;
    }

    public Queue<Missao> getMissoesPendentes() {
        return missoesPendentes;
    }
}
