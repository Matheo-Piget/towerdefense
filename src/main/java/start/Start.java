package src.main.java.start;

import java.util.Scanner;
import src.main.java.configMap.GameMap;
import src.main.java.UI.GUI.*;
import src.main.java.UI.TerminalUI;

public class Start {

    private GameMap map;
    private Player player;
    private TerminalUI terminalUI;
    private GUI gui;

    public Start() {
        map = new GameMap(5, 10);
        player = new Player(1000, 3);
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

        scanner.close();
    }

    public static void main(String[] args) {

        Start game = new Start();
        game.startGame();

    }
}
