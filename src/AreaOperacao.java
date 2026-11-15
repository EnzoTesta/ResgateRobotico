package resgate;

import java.io.Serializable;

public class AreaOperacao implements Serializable {
    private int id;
    private String nome;
    private String localizacao;
    private TipoTerreno tipoTerreno;
    private NivelRisco nivelRisco;
    private String descricao;

    public void atualizarNivelRisco(NivelRisco novoNivel) { }
    public void exibirInformacoes() { }
}
