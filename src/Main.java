
public class Main {

    public static void main(String[] args) {

        System.out.println("==============================================================");
        System.out.println("   SISTEMA DE COORDENAÇÃO DE ROBÔS PARA MISSÕES DE RESGATE    ");
        System.out.println("==============================================================\n");

        String arquivoBackup = "dados_central.dat";

        // 1. Recuperação ou criação da central
        CentralResgate central = CentralResgate.carregarDados(arquivoBackup);
        boolean centralNova = false;

        if (central == null) {
            central = new CentralResgate("Central de Operações de Defesa Civil");
            centralNova = true;
            System.out.println("Nova central criada.");
        } else {
            System.out.println("Central recuperada com sucesso!");
        }

        System.out.println(">> Central: " + central.getNome());

        if (centralNova) {

            // 2. Cadastro de áreas
            System.out.println("\n1. CADASTRO DE ÁREAS DE OPERAÇÃO");

            AreaOperacao area1 = new AreaOperacao(
                    1, "Encosta do Morro", "Região Serrana",
                    TipoTerreno.IRREGULAR, NivelRisco.ALTO,
                    "Risco crítico de deslizamento de terra");

            AreaOperacao area2 = new AreaOperacao(
                    2, "Centro Histórico", "Zona Urbana",
                    TipoTerreno.ALAGADO, NivelRisco.MEDIO,
                    "Ruas inundadas após fortes chuvas");

            central.cadastrarArea(area1);
            central.cadastrarArea(area2);

            // 3. Cadastro de robôs
            System.out.println("\n2. CADASTRO DE ROBÔS");

            Drone drone1 = new Drone(101, "Drone Falcão-01", 85.0, 120.0);
            RoboTerrestre robo1 = new RoboTerrestre(201, "Terrestre Titã-01", 90.0, 60.0);

            central.cadastrarRobo(drone1);
            central.cadastrarRobo(robo1);

            // 4. Registro de missões
            System.out.println("\n3. REGISTRO DE MISSÕES");

            Missao m1 = new Missao(
                    1, "Reconhecimento aéreo da encosta",
                    TipoMissao.RECONHECIMENTO, area1, PrioridadeMissao.ALTA);

            Missao m2 = new Missao(
                    2, "Entrega de kits de primeiros socorros",
                    TipoMissao.TRANSPORTE_SUPRIMENTOS, area2, PrioridadeMissao.MEDIA);

            Missao m3 = new Missao(
                    3, "Localização de pessoas isoladas",
                    TipoMissao.LOCALIZACAO_PESSOAS, area2, PrioridadeMissao.ALTA);

            central.registrarMissao(m1);
            central.registrarMissao(m2);
            central.registrarMissao(m3);

            // 5. Visualização
            System.out.println("\n4. ESTADO ATUAL DOS ROBÔS E DA FILA");
            central.listarRobosDisponiveis();
            central.listarMissoesPendentes();

            // 6. Teste de atribuição inválida
            System.out.println("\n5. TESTE DE REGRA DE NEGÓCIO");

            try {
                central.atribuirRoboMissao(101, 2);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Exceção capturada: " + e.getMessage());
            }

            // 7. Execução manual
            System.out.println("\n6. EXECUÇÃO MANUAL DE MISSÃO");

            central.atribuirRoboMissao(101, 1);
            central.executarMissao(
                    1, "Fotos térmicas geradas com sucesso. Área segura identificada.");

            // 8. Funcionalidade inovadora
            System.out.println("\n7. DESPACHO INTELIGENTE AUTOMÁTICO");

            central.atenderProximaMissaoAutomaticamente(
                    "Medicamentos e água entregues no abrigo central.");

            // 9. Relatório
            System.out.println("\n8. GERAÇÃO DE RELATÓRIO");

            Relatorio relatorio = central.gerarRelatorioGeral();
            relatorio.salvarEmArquivo("relatorio_geral.txt");

        } else {
            // Exibe os dados recuperados
            System.out.println("\n=== DADOS RECUPERADOS ===");
            central.listarRobosDisponiveis();
            central.listarMissoesPendentes();
            central.gerarRelatorioGeral();
        }

        // 10. Salvamento da central
        System.out.println("\n9. SALVAMENTO DOS DADOS");

        central.salvarDados(arquivoBackup);

        System.out.println("\n==============================================================");
        System.out.println("   SIMULAÇÃO FINALIZADA COM SUCESSO!                          ");
        System.out.println("==============================================================");
    }
}
