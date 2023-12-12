package src.main.java.UI;

import java.util.Scanner;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

public class TerminalUI {

    GameMap map;
    Player player;

    public TerminalUI(GameMap map, Player player){

        this.map = map;
        this.player = player;


    }

    public void affiche_menu(){ // Menu du jeu

        Scanner scanner = new Scanner(System.in);
        int choix;

        System.out.println("===== MENU DU JEU =====");
        System.out.println("1. Démarrer le jeu");
        System.out.println("2. Reprendre je jeu");
        System.out.println("3. Options");
        System.out.println("4. Scores");
        System.out.println("5. Quitter");

        System.out.print("Entrez votre choix : "); // pour choisir ce que l'on veut faire
        choix = scanner.nextInt();

        switch (choix) {
            case 1:
                System.out.println("Le jeu démarre !");
                start();
                break;
            case 2:
                System.out.println("Reprise du jeu");
                break;
            case 3:
                System.out.println("Options du jeu");
                break;
            case 4:
                System.out.println("Scores du jeu");
                break;
            case 5:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Choix invalide. Veuillez choisir une option valide.");
                break;
        }

        scanner.close(); // fermeture de la lecture de la ligne du terminal
    }

    public void startTerminalGame(GameMap map, Player player) {

        affiche_menu(); // au démarrage on affiche le menu

    }

    public void start(){

        while (player.getLives() > 0) {


            System.out.println("Choisissez une action : ");

            Scanner scanner = new Scanner(System.in);

            //on met a jour la frame selon le choix du joueur, a toi de faire 
            //si tel choix est mis dans le terminal alors on afffiche le menu

            scanner.close();


            map.update();// on met jour la map : les nouveaux enemies, les enemies mort etc..
            player.update();// on met jour le joueur : si il a perdu une vie, gagné de l'argent etc...

            player.affiche(); // on affiche la map et les données du joueur
            map.affiche();
            

        }
        

    }

}
