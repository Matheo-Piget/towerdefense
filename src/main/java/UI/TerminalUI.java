package src.main.java.UI;

import java.util.Scanner;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

public class TerminalUI {

    GameMap map;
    Player player;
    private final Scanner scanner; 
    static int difficulté;

    public TerminalUI(GameMap map, Player player){

        this.map = map;
        this.player = player;
        this.scanner = new Scanner(System.in); // on initialise un champ scannaer pour gerer tout les choix via le terminal de l'utitlisateur


    }

    public void affiche_menu() {
        int choix;

        System.out.println("===== MENU DU JEU =====");
        System.out.println("1. Démarrer le jeu");
        System.out.println("2. Reprendre le jeu");
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
                gererOptions();
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
    }

    private void gererOptions() {

        int choixOption;

        System.out.println("===== OPTIONS =====");
        System.out.println("1. Choisir la difficulté");

        System.out.print("Entrez votre choix : ");
        choixOption = scanner.nextInt(); // on demande a l'utilisateur le choix de l'option qu'il veut modifier

        switch (choixOption) {
            case 1:
                choisirDifficulte();
                break;
            default:
                System.out.println("Choix invalide.");
                break;
        }
    }

    private void choisirDifficulte() {

        int choixDifficulte;

        System.out.println("Choisissez la difficulté :");
        System.out.println("1. Facile");
        System.out.println("2. Moyen");
        System.out.println("3. Difficile");

        System.out.print("Entrez votre choix : ");
        choixDifficulte = scanner.nextInt();

        // Implémentez la logique pour régler la difficulté ici en fonction de choixDifficulte
        // Par exemple, ajuster les paramètres du jeu en fonction de la difficulté choisie

        System.out.println("Difficulté réglée avec succès !");
        affiche_menu(); // Revenir au menu principal
    }

    public void startTerminalGame(GameMap map, Player player) {

        affiche_menu(); // au démarrage on affiche le menu

    }

    public void start(){

        while (player.getLives() > 0) {


            System.out.println("Choisissez une action : ");
            System.out.println("(P) placer une tour, (esc) retourner au menu, ()");

            String choix = scanner.nextLine();

            switch (choix) {

                case "esc":

                    affiche_menu();
                    break;

                case "P":

                    
            
                default:


                    break;
            }

            //on met a jour la frame selon le choix du joueur, a toi de faire 
            //si tel choix est mis dans le terminal alors on afffiche le menu


            map.update();// on met jour la map : les nouveaux enemies, les enemies mort etc..
            player.update();// on met jour le joueur : si il a perdu une vie, gagné de l'argent etc...

            player.affiche(); // on affiche la map et les données du joueur
            map.affiche();
            

        }
        

    }

}
