package resgate;

public class Drone extends Robo {
    private double altitudeMaxima;
    private double alcanceVoo;
    private boolean captaImagens;

    public void executarMissao(Missao missao) { }
    public boolean podeExecutar(TipoMissao tipo) { return false; }
    protected double calcularConsumo(Missao missao) { return 0; }
}
