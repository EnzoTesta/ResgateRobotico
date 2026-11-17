import java.io.Serializable;

public class Missao implements Serializable {

    private int id;
    private String descricao;
    private TipoMissao tipo;
    private AreaOperacao area;
    private PrioridadeMissao prioridade;
    private StatusMissao status;
    private Robo roboResponsavel;
    private String resultado;

    public Missao(int id, String descricao, TipoMissao tipo, AreaOperacao area, PrioridadeMissao prioridade) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID da missão deve ser maior que zero.");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da missão não pode ser vazia.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo de missão não pode ser nulo.");
        }
        if (area == null) {
            throw new IllegalArgumentException("A missão deve estar associada a uma área de operação.");
        }
        if (prioridade == null) {
            throw new IllegalArgumentException("A prioridade da missão não pode ser nula.");
        }

        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.area = area;
        this.prioridade = prioridade;
        this.status = StatusMissao.PENDENTE;
        this.roboResponsavel = null;
        this.resultado = "Não executada";
    }

    public void atribuirRobo(Robo robo) {
        if (robo == null) {
            throw new IllegalArgumentException("O robô não pode ser nulo.");
        }
        if (this.status != StatusMissao.PENDENTE) {
            throw new IllegalStateException("Só é possível atribuir robô a uma missão pendente.");
        }
        if (!robo.verificarDisponibilidade()) {
            throw new IllegalStateException("O robô " + robo.getNome() + " não está disponível.");
        }
        if (!robo.podeExecutar(this.tipo)) {
            throw new IllegalStateException(
                    "O robô " + robo.getNome() + " não é compatível com o tipo de missão " + this.tipo + ".");
        }
        if (robo.consultarBateria() < robo.calcularConsumo(this)) {
            throw new IllegalStateException(
                    "O robô " + robo.getNome() + " não possui bateria suficiente para esta missão.");
        }

        this.roboResponsavel = robo;
        robo.receberMensagem("Você foi designado para a missão: " + this.descricao);
        System.out.println("Robô " + robo.getNome() + " atribuído com sucesso à missão " + this.id + ".");
    }

    public void iniciar() {
        if (this.status != StatusMissao.PENDENTE) {
            throw new IllegalStateException("Apenas missões pendentes podem ser iniciadas.");
        }
        if (this.roboResponsavel == null) {
            throw new IllegalStateException("A missão não pode ser iniciada sem um robô atribuído.");
        }

        this.status = StatusMissao.EM_ANDAMENTO;
        this.roboResponsavel.enviarMensagem("Iniciando execução da missão na área: " + this.area.getNome());
        System.out.println("Missão " + this.id + " em andamento.");

        // O robô executa a missão (consome bateria e atualiza suas missões realizadas)
        this.roboResponsavel.executarMissao(this);
    }

    public void concluir(String resultado) {
        if (this.status != StatusMissao.EM_ANDAMENTO) {
            throw new IllegalStateException("Apenas missões em andamento podem ser concluídas.");
        }
        if (resultado == null || resultado.trim().isEmpty()) {
            throw new IllegalArgumentException("O resultado da missão não pode ser vazio.");
        }

        this.status = StatusMissao.CONCLUIDA;
        this.resultado = resultado;

        if (this.roboResponsavel != null) {
            this.roboResponsavel.enviarMensagem("Missão concluída. Resultado: " + resultado);
        }

        System.out.println("Missão " + this.id + " concluída com sucesso.");
    }

    public void cancelar() {
        if (this.status == StatusMissao.CONCLUIDA) {
            throw new IllegalStateException("Não é possível cancelar uma missão que já foi concluída.");
        }
        if (this.status == StatusMissao.CANCELADA) {
            throw new IllegalStateException("A missão já está cancelada.");
        }

        this.status = StatusMissao.CANCELADA;
        this.resultado = "Missão cancelada";

        if (this.roboResponsavel != null) {
            this.roboResponsavel.enviarMensagem("Missão " + this.id + " foi cancelada.");
            if (this.roboResponsavel.getStatus() == StatusRobo.EM_MISSAO) {
                if (this.roboResponsavel.consultarBateria() > 0) {
                    this.roboResponsavel.setStatus(StatusRobo.DISPONIVEL);
                } else {
                    this.roboResponsavel.setStatus(StatusRobo.SEM_BATERIA);
                }
            }
        }

        System.out.println("Missão " + this.id + " foi cancelada.");
    }

    public void exibirResumo() {
        System.out.println("--- Resumo da Missão ---");
        System.out.println("ID: " + id);
        System.out.println("Descrição: " + descricao);
        System.out.println("Tipo: " + tipo);
        System.out.println("Área: " + (area != null ? area.getNome() : "Não definida"));
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Status: " + status);
        System.out.println("Robô Responsável: " + (roboResponsavel != null ? roboResponsavel.getNome() : "Nenhum"));
        System.out.println("Resultado: " + resultado);
    }

    // Getters e setters

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da missão não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public TipoMissao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMissao tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo de missão não pode ser nulo.");
        }
        this.tipo = tipo;
    }

    public AreaOperacao getArea() {
        return area;
    }

    public void setArea(AreaOperacao area) {
        if (area == null) {
            throw new IllegalArgumentException("A área de operação não pode ser nula.");
        }
        this.area = area;
    }

    public PrioridadeMissao getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeMissao prioridade) {
        if (prioridade == null) {
            throw new IllegalArgumentException("A prioridade não pode ser nula.");
        }
        this.prioridade = prioridade;
    }

    public StatusMissao getStatus() {
        return status;
    }

    public void setStatus(StatusMissao status) {
        this.status = status;
    }

    public Robo getRoboResponsavel() {
        return roboResponsavel;
    }

    public void setRoboResponsavel(Robo roboResponsavel) {
        this.roboResponsavel = roboResponsavel;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
