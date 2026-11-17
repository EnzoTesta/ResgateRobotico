
public class RoboTerrestre extends Robo {

    private double capacidadeCarga;

    public RoboTerrestre(int id, String nome, double nivelBateria,
                         double capacidadeCarga) {

        super(id, nome, nivelBateria);

        if (capacidadeCarga <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade de carga deve ser maior que zero."
            );
        }

        this.capacidadeCarga = capacidadeCarga;
    }

    @Override
    public boolean podeExecutar(TipoMissao tipo) {

        return tipo == TipoMissao.TRANSPORTE_SUPRIMENTOS
                || tipo == TipoMissao.RECONHECIMENTO;
    }

    @Override
    protected double calcularConsumo(Missao missao) {
        return 20.0;
    }

    @Override
    public void executarMissao(Missao missao) {
        if (missao == null) {
            throw new IllegalArgumentException("A missão não pode ser nula.");
        }
        if (!verificarDisponibilidade()) {
            throw new IllegalStateException(
                    "Robô terrestre não está disponível."
            );
        }

        double consumo = calcularConsumo(missao);

        if (consultarBateria() < consumo) {
            throw new IllegalStateException(
                    "Bateria insuficiente para executar a missão."
            );
        }

        setStatus(StatusRobo.EM_MISSAO);

        atualizarBateria(consumo);

        incrementarMissoesRealizadas();

        if (consultarBateria() > 0) {
            setStatus(StatusRobo.DISPONIVEL);
        }
    }

    public double getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(double capacidadeCarga) {

        if (capacidadeCarga <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade de carga deve ser maior que zero."
            );
        }

        this.capacidadeCarga = capacidadeCarga;
    }
}
