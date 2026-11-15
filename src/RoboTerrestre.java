package resgate;

public class RoboTerrestre extends Robo {
    private double capacidadeCarga;
    private TipoTerreno terrenoSuportado;
    private boolean superaObstaculos;

    public void executarMissao(Missao missao) { }
    public boolean podeExecutar(TipoMissao tipo) { return false; }
    protected double calcularConsumo(Missao missao) { return 0; }
}
