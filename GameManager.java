import java.util.Scanner;

public class GameManager {

    private GameMap map;
    private Player player;
    private TerminalUI terminalUI;
    private GUI gui;

    public GameManager() {
        map = new GameMap(/* rows, cols */);
        player = new Player(/* initial parameters */);
        terminalUI = new TerminalUI();
        gui = new GUI();
    }

    public void startGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez votre interface (T) Terminal ou (G) Graphique:");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("T")) {

            terminalUI.startTerminalGame(map, player);

        } else if (choice.equalsIgnoreCase("G")) {

            gui.startGUIGame(map, player);

        } else {

            System.out.println("Choix invalide, veuillez sélectionner T ou G.");
            
        }
    }
}
