public class Main {

    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("   SISTEMA DE COORDENAÇÃO DE ROBÔS PARA MISSÕES DE RESGATE    ");
        System.out.println("==============================================================\n");

        // 1. Criação da Central de Resgate
        CentralResgate central = new CentralResgate("Central de Operações de Defesa Civil");
        System.out.println(">> Central criada: " + central.getNome());

        // 2. Cadastro de Áreas de Operação
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("1. CADASTRO DE ÁREAS DE OPERAÇÃO");
        System.out.println("--------------------------------------------------------------");
        AreaOperacao area1 = new AreaOperacao(1, "Encosta do Morro", "Região Serrana", TipoTerreno.IRREGULAR, NivelRisco.ALTO, "Risco crítico de deslizamento de terra");
        AreaOperacao area2 = new AreaOperacao(2, "Centro Histórico", "Zona Urbana", TipoTerreno.ALAGADO, NivelRisco.MEDIO, "Ruas inundadas após fortes chuvas");
        
        central.cadastrarArea(area1);
        central.cadastrarArea(area2);

        // 3. Cadastro de Robôs (Drone e Terrestre)
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("2. CADASTRO DE ROBÔS");
        System.out.println("--------------------------------------------------------------");
        Drone drone1 = new Drone(101, "Drone Falcão-01", 85.0, 120.0);
        RoboTerrestre robo1 = new RoboTerrestre(201, "Terrestre Titã-01", 90.0, 60.0);

        central.cadastrarRobo(drone1);
        central.cadastrarRobo(robo1);

        // 4. Registro de Missões
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("3. REGISTRO DE MISSÕES (VÃO PARA A FILA DE PENDENTES)");
        System.out.println("--------------------------------------------------------------");
        Missao m1 = new Missao(1, "Reconhecimento aéreo da encosta", TipoMissao.RECONHECIMENTO, area1, PrioridadeMissao.ALTA);
        Missao m2 = new Missao(2, "Entrega de kits de primeiros socorros", TipoMissao.TRANSPORTE_SUPRIMENTOS, area2, PrioridadeMissao.MEDIA);
        Missao m3 = new Missao(3, "Localização de pessoas isoladas", TipoMissao.LOCALIZACAO_PESSOAS, area2, PrioridadeMissao.ALTA);

        central.registrarMissao(m1);
        central.registrarMissao(m2);
        central.registrarMissao(m3);

        // 5. Visualizar Robôs e Fila de Missões
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("4. ESTADO ATUAL DOS ROBÔS E DA FILA");
        System.out.println("--------------------------------------------------------------");
        central.listarRobosDisponiveis();
        central.listarMissoesPendentes();

        // 6. Teste de Validação e Tratamento de Exceções (Regra 3 do PDF)
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("5. TESTE DE REGRA DE NEGÓCIO (TENTATIVA INVÁLIDA)");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Tentando atribuir Drone Falcão-01 para missão de TRANSPORTE_SUPRIMENTOS (Incompatível)...");
        try {
            central.atribuirRoboMissao(101, 2);
        } catch (IllegalStateException e) {
            System.out.println(">> Exceção capturada com sucesso: " + e.getMessage());
        }

        // 7. Atribuição e Execução Manual da Missão 1
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("6. ATRIBUIÇÃO E EXECUÇÃO MANUAL DE MISSÃO");
        System.out.println("--------------------------------------------------------------");
        central.atribuirRoboMissao(101, 1); // Drone compatível com Reconhecimento
        central.executarMissao(1, "Fotos térmicas geradas com sucesso. Área segura identificada.");

        // 8. Demonstração da FUNCIONALIDADE INOVADORA
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("7. FUNCIONALIDADE INOVADORA: DESPACHO INTELIGENTE AUTOMÁTICO");
        System.out.println("--------------------------------------------------------------");
        System.out.println("A central consulta a fila e encontra o melhor robô disponível por bateria...");
        central.atenderProximaMissaoAutomaticamente("Medicamentos e água entregues no abrigo central.");

        // 9. Relatório Geral e Exportação em Arquivo
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("8. GERAÇÃO DE RELATÓRIO E PERSISTÊNCIA EM ARQUIVO");
        System.out.println("--------------------------------------------------------------");
        Relatorio relatorio = central.gerarRelatorioGeral();
        relatorio.salvarEmArquivo("relatorio_geral.txt");

        // 10. Persistência dos Dados da Central (Regra 10 do PDF)
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("9. SALVAMENTO E RECUPERAÇÃO DO SISTEMA (SERIALIZAÇÃO)");
        System.out.println("--------------------------------------------------------------");
        String arquivoBackup = "dados_central.dat";
        central.salvarDados(arquivoBackup);

        System.out.println("\nRecuperando dados da central a partir do arquivo...");
        CentralResgate centralRecuperada = CentralResgate.carregarDados(arquivoBackup);
        if (centralRecuperada != null) {
            System.out.println("Central recuperada: " + centralRecuperada.getNome());
            System.out.println("Total de robôs recuperados: " + centralRecuperada.getRobos().size());
            System.out.println("Total de missões recuperadas: " + centralRecuperada.getMissoes().size());
            System.out.println("Missões restantes na fila recuperada: " + centralRecuperada.getMissoesPendentes().size());
        }

        System.out.println("\n==============================================================");
        System.out.println("   SIMULAÇÃO FINALIZADA COM SUCESSO!                          ");
        System.out.println("==============================================================");
    }
}

