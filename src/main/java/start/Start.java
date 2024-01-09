package src.main.java.start;

import java.util.Scanner;
import src.main.java.UI.GUI.*;
import src.main.java.UI.TerminalUI;
import src.main.java.configMap.GameMap;

public class Start {

  private GameMap map;
  private Player player;
  private TerminalUI terminalUI;

  public Start() {
    map = new GameMap(5, 10);
    player = new Player(200, 10);
    terminalUI = new TerminalUI(map, player);
  }

  public void startGame() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\033\143");
    System.out.println("Bienvenue dans le jeu ProtectiveTowers");
    System.out.println(
      "Choisissez votre interface (T) Terminal ou (G) Graphique:"
    );

    String choice = scanner.nextLine();

    if (choice.equalsIgnoreCase("T")) {
      System.out.println("\033\143");
      terminalUI.startTerminalGame(map, player);
    } else if (choice.equalsIgnoreCase("G")) {
      System.out.println("\033\143");
      System.out.println(
        "Très bien, le jeu va se lancer dans une nouvelle fenêtre."
      );
      GUI gui = new GUI();
      gui.startGUIGame(map, player);
    } else if (choice.equalsIgnoreCase("Q")) {
      System.out.println("\033\143");
      System.out.println("Au revoir !");
      System.exit(0);
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
