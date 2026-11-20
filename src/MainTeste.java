
public class MainTeste {

    public static void main(String[] args) {

        Drone drone = new Drone(1, "Drone Alpha", 100, 120);

        RoboTerrestre terrestre = new RoboTerrestre(
                2, "Robo Beta", 80, 50
        );



        System.out.println("\n===== TESTE DE POLIMORFISMO =====");

        Robo robo1 = new Drone(4, "Drone Delta", 100, 150);
        Robo robo2 = new RoboTerrestre(5, "Robo Epsilon", 90, 80);

        robo1.exibirResumo();
        robo2.exibirResumo();

        System.out.println("\nTipo do primeiro objeto: " +
                robo1.getClass().getSimpleName());

        System.out.println("Tipo do segundo objeto: " +
                robo2.getClass().getSimpleName());



    }
}
