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
        player = new Player(50, 3);
        terminalUI = new TerminalUI(map, player);
    }

    public void startGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\033\143");
        System.out.println("Bienvenue dans le jeu ProtectiveTowers");
        System.out.println("Pour lire les règles du jeu, merci d'entrer (R)");
        System.out.println("Choisissez votre interface (T) Terminal ou (G) Graphique:");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("T")) {
            System.out.println("\033\143");
            terminalUI.startTerminalGame(map, player);
        } else if (choice.equalsIgnoreCase("G")) {
            GUI.main(new String[0]);
        } else if (choice.equalsIgnoreCase("R")) {
            // TODO : afficher les règles du jeu
            System.exit(0);
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
