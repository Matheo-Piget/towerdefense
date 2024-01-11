package src.main.java.start;

import java.util.Scanner;
import src.main.java.configMap.GameMap;
import src.main.java.UI.GUI.*;
import src.main.java.UI.TerminalUI;

public class Start {

    private GameMap map;
    private Player player;
    private TerminalUI terminalUI;

    public Start() {
        map = new GameMap(5, 10);
        player = new Player(700, 10);
    }

    public void startGame() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\033\143");
        System.out.println("Bienvenue dans le jeu ProtectiveTowers");
        System.out.println("Choisissez votre interface (T) Terminal ou (G) Graphique:");

        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("T")) {
            System.out.println("\033\143");
            var party = new TerminalUI(map, player);
            party.startTerminalGame();
        } else if (choice.equalsIgnoreCase("G")) {
            System.out.println("\033\143");
            System.out.println("Très bien, le jeu va se lancer dans une nouvelle fenêtre.");
            var party = new GUI(map, player);
            party.startGUIGame();
        } else if (choice.equalsIgnoreCase("Q")) {
            System.out.println("\033\143");
            System.out.println("Au revoir !");
            System.exit(0);
        }

        else {

            System.out.println("Choix invalide, veuillez sélectionner T ou G.");

        }

        scanner.close();
    }

    public static void main(String[] args) {

        Start game = new Start();
        game.startGame();

    }
}
