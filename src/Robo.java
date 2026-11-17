
import java.io.Serializable;

public abstract class Robo implements Comunicavel, Serializable {

    private int id;
    private String nome;
    private double nivelBateria;
    private StatusRobo status;
    private int missoesRealizadas;

    public Robo(int id, String nome, double nivelBateria) {

        if (nivelBateria < 0 || nivelBateria > 100) {
            throw new IllegalArgumentException(
                    "A bateria deve estar entre 0 e 100."
            );
        }

        this.id = id;
        this.nome = nome;
        this.nivelBateria = nivelBateria;
        this.missoesRealizadas = 0;

        if (nivelBateria == 0) {
            this.status = StatusRobo.SEM_BATERIA;
        } else {
            this.status = StatusRobo.DISPONIVEL;
        }
    }

    public boolean verificarDisponibilidade() {
        return status == StatusRobo.DISPONIVEL
                && nivelBateria > 0;
    }

    public double consultarBateria() {
        return nivelBateria;
    }

    public void recarregar() {
        nivelBateria = 100;

        if (status == StatusRobo.SEM_BATERIA) {
            status = StatusRobo.DISPONIVEL;
        }
    }

    public void exibirResumo() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Bateria: " + nivelBateria + "%");
        System.out.println("Status: " + status);
        System.out.println("Missões realizadas: " + missoesRealizadas);
    }

    public abstract void executarMissao(Missao missao);

    public abstract boolean podeExecutar(TipoMissao tipo);

    protected abstract double calcularConsumo(Missao missao);

    public void enviarMensagem(String mensagem) {
        System.out.println(nome + " enviou: " + mensagem);
    }

    public void receberMensagem(String mensagem) {
        System.out.println(nome + " recebeu: " + mensagem);
    }

    // Getters e setters

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getNivelBateria() {
        return nivelBateria;
    }

    public StatusRobo getStatus() {
        return status;
    }

    public int getMissoesRealizadas() {
        return missoesRealizadas;
    }

    public void setStatus(StatusRobo status) {
        this.status = status;
    }

    public void setNivelBateria(double nivelBateria) {
        if (nivelBateria < 0 || nivelBateria > 100) {
            throw new IllegalArgumentException(
                    "A bateria deve estar entre 0 e 100."
            );
        }

        this.nivelBateria = nivelBateria;

        if (nivelBateria == 0) {
            this.status = StatusRobo.SEM_BATERIA;
        }
    }

    protected void atualizarBateria(double consumo) {
        if (consumo < 0 || consumo > nivelBateria) {
            throw new IllegalArgumentException(
                    "Consumo de bateria inválido."
            );
        }

        nivelBateria -= consumo;

        if (nivelBateria == 0) {
            status = StatusRobo.SEM_BATERIA;
        }
    }

    protected void incrementarMissoesRealizadas() {
        missoesRealizadas++;
    }
}
