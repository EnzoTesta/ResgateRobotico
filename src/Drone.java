
public class Drone extends Robo {

    private double altitudeMaxima;

    public Drone(int id, String nome, double nivelBateria,
                 double altitudeMaxima) {

        super(id, nome, nivelBateria);

        if (altitudeMaxima <= 0) {
            throw new IllegalArgumentException(
                    "A altitude máxima deve ser maior que zero."
            );
        }

        this.altitudeMaxima = altitudeMaxima;
    }

    @Override
    public boolean podeExecutar(TipoMissao tipo) {

        return tipo == TipoMissao.RECONHECIMENTO
                || tipo == TipoMissao.LOCALIZACAO_PESSOAS;
    }

    @Override
    protected double calcularConsumo(Missao missao) {
        return 15.0;
    }

    @Override
    public void executarMissao(Missao missao) {
        if (missao == null) {
            throw new IllegalArgumentException("A missão não pode ser nula.");
        }
        if (!verificarDisponibilidade()) {
            throw new IllegalStateException(
                    "Drone não está disponível."
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

    public double getAltitudeMaxima() {
        return altitudeMaxima;
    }

    public void setAltitudeMaxima(double altitudeMaxima) {

        if (altitudeMaxima <= 0) {
            throw new IllegalArgumentException(
                    "A altitude máxima deve ser maior que zero."
            );
        }

        this.altitudeMaxima = altitudeMaxima;
    }
}
