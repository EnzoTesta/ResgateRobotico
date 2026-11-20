import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Relatorio implements Serializable {

    private LocalDateTime dataGeracao;
    private int totalRobos;
    private int totalMissoes;
    private int missoesConcluidas;
    private int missoesPendentes;
    private int missoesCanceladas;
    private int robosDisponiveis;

    public Relatorio(int totalRobos, int robosDisponiveis, int totalMissoes,
                     int missoesConcluidas, int missoesPendentes, int missoesCanceladas) {
        this.dataGeracao = LocalDateTime.now();
        this.totalRobos = totalRobos;
        this.robosDisponiveis = robosDisponiveis;
        this.totalMissoes = totalMissoes;
        this.missoesConcluidas = missoesConcluidas;
        this.missoesPendentes = missoesPendentes;
        this.missoesCanceladas = missoesCanceladas;
    }

    public String gerarResumo() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();

        sb.append("=========================================\n");
        sb.append("     RELATÓRIO GERAL DA CENTRAL          \n");
        sb.append("=========================================\n");
        sb.append("Data e Hora de Geração: ").append(dataGeracao.format(formato)).append("\n");
        sb.append("-----------------------------------------\n");
        sb.append("Total de Robôs Cadastrados: ").append(totalRobos).append("\n");
        sb.append("Robôs Disponíveis: ").append(robosDisponiveis).append("\n");
        sb.append("Total de Missões Registradas: ").append(totalMissoes).append("\n");
        sb.append("Missões Concluídas: ").append(missoesConcluidas).append("\n");
        sb.append("Missões Pendentes na Fila: ").append(missoesPendentes).append("\n");
        sb.append("Missões Canceladas: ").append(missoesCanceladas).append("\n");
        sb.append("=========================================\n");

        return sb.toString();
    }

        public void salvarEmArquivo(String caminhoArquivo) {
            if (caminhoArquivo == null || caminhoArquivo.trim().isEmpty()) {
                throw new IllegalArgumentException("O caminho do arquivo não pode ser vazio.");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
                writer.write(gerarResumo());
                System.out.println("Relatório salvo com sucesso em: " + caminhoArquivo);
            } catch (IOException e) {
                System.err.println("Erro ao salvar o relatório em arquivo: " + e.getMessage());
            }
        }

    // Getters

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public int getTotalRobos() {
        return totalRobos;
    }

    public int getRobosDisponiveis() {
        return robosDisponiveis;
    }

    public int getTotalMissoes() {
        return totalMissoes;
    }

    public int getMissoesConcluidas() {
        return missoesConcluidas;
    }

    public int getMissoesPendentes() {
        return missoesPendentes;
    }

    public int getMissoesCanceladas() {
        return missoesCanceladas;
    }
}
