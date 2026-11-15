package resgate;

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

    public void atribuirRobo(Robo robo) { }
    public void iniciar() { }
    public void concluir() { }
    public void cancelar() { }
    public void exibirResumo() { }
}
