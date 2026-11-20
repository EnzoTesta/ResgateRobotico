
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String ARQUIVO = "dados_central.dat";

    public static void main(String[] args) {

        CentralResgate central = CentralResgate.carregarDados(ARQUIVO);

        if (central == null) {
            central = new CentralResgate("Central de Resgate");
            System.out.println("Nova central criada!");
        }

        int opcao;

        do {
            System.out.println("\n======================================");
            System.out.println("       SISTEMA DE RESGATE ROBÓTICO");
            System.out.println("======================================");
            System.out.println("1  - Listar robôs");
            System.out.println("2  - Listar áreas de operação");
            System.out.println("3  - Listar missões");
            System.out.println("4  - Listar missões pendentes");
            System.out.println("5  - Gerar relatório geral");
            System.out.println("6  - Despacho inteligente automático");
            System.out.println("7  - Atribuir robô manualmente");
            System.out.println("8  - Executar missão");
            System.out.println("9  - Cancelar missão");
            System.out.println("10 - Salvar dados");
            System.out.println("0  - Sair");
            System.out.println("======================================");

            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {

                    case 1:
                        listarRobos(central);
                        break;

                    case 2:
                        listarAreas(central);
                        break;

                    case 3:
                        listarMissoes(central);
                        break;

                    case 4:
                        central.listarMissoesPendentes();
                        break;

                    case 5:
                        central.gerarRelatorioGeral();
                        break;

                    case 6:
                        central.atenderProximaMissaoAutomaticamente(
                                "Missão concluída com sucesso."
                        );
                        central.salvarDados(ARQUIVO);
                        break;

                    case 7:
                        int idRobo = lerInteiro("Digite o ID do robô: ");
                        int idMissao = lerInteiro("Digite o ID da missão: ");

                        central.atribuirRoboMissao(idRobo, idMissao);
                        central.salvarDados(ARQUIVO);
                        break;

                    case 8:
                        int idExecucao = lerInteiro(
                                "Digite o ID da missão: "
                        );

                        String resultado = lerTexto(
                                "Digite o resultado da missão: "
                        );

                        central.executarMissao(idExecucao, resultado);
                        central.salvarDados(ARQUIVO);
                        break;

                    case 9:
                        int idCancelamento = lerInteiro(
                                "Digite o ID da missão: "
                        );

                        central.cancelarMissao(idCancelamento);
                        central.salvarDados(ARQUIVO);
                        break;

                    case 10:
                        central.salvarDados(ARQUIVO);
                        break;

                    case 0:
                        central.salvarDados(ARQUIVO);
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (IllegalArgumentException |
                     IllegalStateException e) {

                System.out.println("Erro: " + e.getMessage());

            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void listarRobos(CentralResgate central) {

        System.out.println("\n=== ROBÔS CADASTRADOS ===");

        if (central.getRobos().isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }

        central.getRobos().values().forEach(Robo::exibirResumo);
    }

    private static void listarAreas(CentralResgate central) {

        System.out.println("\n=== ÁREAS DE OPERAÇÃO ===");

        if (central.getAreas().isEmpty()) {
            System.out.println("Nenhuma área cadastrada.");
            return;
        }

        central.getAreas().forEach(area ->
                System.out.println(
                        "ID: " + area.getId() +
                                " | Nome: " + area.getNome()
                )
        );
    }

    private static void listarMissoes(CentralResgate central) {

        System.out.println("\n=== MISSÕES CADASTRADAS ===");

        if (central.getMissoes().isEmpty()) {
            System.out.println("Nenhuma missão cadastrada.");
            return;
        }

        central.getMissoes().forEach(Missao::exibirResumo);
    }

    private static int lerInteiro(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido!");
            }
        }
    }

    private static String lerTexto(String mensagem) {

        System.out.print(mensagem);
        return scanner.nextLine();
    }
}
