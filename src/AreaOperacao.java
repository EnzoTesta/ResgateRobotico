import java.io.Serializable;

public class AreaOperacao implements Serializable {

    private int id;
    private String nome;
    private String localizacao;
    private TipoTerreno tipoTerreno;
    private NivelRisco nivelRisco;
    private String descricao;

    public AreaOperacao(int id, String nome, String localizacao,
                        TipoTerreno tipoTerreno, NivelRisco nivelRisco,
                        String descricao) {

        if (id <= 0) {
            throw new IllegalArgumentException("O ID da área deve ser maior que zero.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da área não pode ser vazio.");
        }
        if (localizacao == null || localizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("A localização não pode ser vazia.");
        }
        if (tipoTerreno == null) {
            throw new IllegalArgumentException("O tipo de terreno não pode ser nulo.");
        }
        if (nivelRisco == null) {
            throw new IllegalArgumentException("O nível de risco não pode ser nulo.");
        }

        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.tipoTerreno = tipoTerreno;
        this.nivelRisco = nivelRisco;
        this.descricao = descricao;
    }

    public void atualizarNivelRisco(NivelRisco novoRisco) {
        if (novoRisco == null) {
            throw new IllegalArgumentException("O novo nível de risco não pode ser nulo.");
        }
        this.nivelRisco = novoRisco;
        System.out.println("Nível de risco da área " + nome + " atualizado para: " + novoRisco);
    }

    public void exibirInformacoes() {
        System.out.println("--- Informações da Área de Operação ---");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Localização: " + localizacao);
        System.out.println("Tipo de Terreno: " + tipoTerreno);
        System.out.println("Nível de Risco: " + nivelRisco);
        System.out.println("Descrição: " + descricao);
    }

    // Getters e setters

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da área não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        if (localizacao == null || localizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("A localização não pode ser vazia.");
        }
        this.localizacao = localizacao;
    }

    public TipoTerreno getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(TipoTerreno tipoTerreno) {
        if (tipoTerreno == null) {
            throw new IllegalArgumentException("O tipo de terreno não pode ser nulo.");
        }
        this.tipoTerreno = tipoTerreno;
    }

    public NivelRisco getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(NivelRisco nivelRisco) {
        if (nivelRisco == null) {
            throw new IllegalArgumentException("O nível de risco não pode ser nulo.");
        }
        this.nivelRisco = nivelRisco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
