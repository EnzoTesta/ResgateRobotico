package resgate;

import java.time.LocalDateTime;

public class Relatorio {
    private LocalDateTime dataGeracao;
    private int totalRobos;
    private int totalMissoes;
    private int missoesConcluidas;
    private int missoesPendentes;

    public String gerarResumo() { return null; }
    public void salvarEmArquivo(String caminho) { }
}
