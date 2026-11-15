package resgate;

import java.io.Serializable;

public abstract class Robo implements Comunicavel, Serializable {
    private int id;
    private String nome;
    private double nivelBateria;
    private StatusRobo status;
    private int missoesRealizadas;

    public boolean verificarDisponibilidade() { return false; }
    public double consultarBateria() { return 0; }
    public void recarregar() { }
    public void exibirResumo() { }
    public abstract void executarMissao(Missao missao);
    public abstract boolean podeExecutar(TipoMissao tipo);
    protected abstract double calcularConsumo(Missao missao);
    public void enviarMensagem(String mensagem) { }
    public void receberMensagem(String mensagem) { }
}
