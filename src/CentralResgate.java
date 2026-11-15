package resgate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CentralResgate implements Serializable {
    private String nome;
    private Map<Integer, Robo> robos;
    private Map<Integer, AreaOperacao> areas;
    private List<Missao> missoes;
    private Queue<Missao> missoesPendentes;

    public void cadastrarRobo(Robo robo) { }
    public void cadastrarArea(AreaOperacao area) { }
    public void registrarMissao(Missao missao) { }
    public List<Robo> listarRobosDisponiveis() { return null; }
    public void atribuirRoboMissao(Missao missao) { }
    public List<Missao> listarMissoesPendentes() { return null; }
    public Relatorio gerarRelatorioGeral() { return null; }
}
