package src.main.java.UI;

import java.util.Scanner;

import src.main.java.configMap.GameMap;
import src.main.java.model.Tower;
import src.main.java.start.Player;

public class TerminalUI {

    GameMap map;
    Player player;
    private final Scanner scanner; 
    public static int difficulté;

    public TerminalUI(GameMap map, Player player){

        this.map = map;
        this.player = player;
        difficulté = 1;
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

        difficulté = choixDifficulte;

        System.out.println("Difficulté réglée avec succès !");
        affiche_menu(); // Revenir au menu principal
    }

    public void startTerminalGame(GameMap map, Player player) {

        affiche_menu(); // au démarrage on affiche le menu

    }

    private void start() {
        boolean gameOver = false;
    
        while (!gameOver) {
            // Mettre à jour la carte
            player.setMoney(map.update(player)+player.getMoney());
    
            // Afficher les informations du joueur et la carte
            player.affiche();// Méthode à créer dans la classe Player pour afficher les infos
            map.affiche(); // Méthode à créer dans la classe GameMap pour afficher la carte
    
            // Afficher le menu pour les actions du joueur pendant le jeu
            displayInGameMenu();

            gameOver = player.getLives() < 0;
            
        }
    }
    
    private void displayInGameMenu() {
        System.out.println("===== MENU D'ACTION =====");
        System.out.println("1. Placer une tour");
        System.out.println("2. Passer le tour");
        System.out.println("3. Retour au Menu");

    
        System.out.print("Entrez votre choix : ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne
    
        switch (choice) {

            case 1:

                placeTower();
                break;

            case 2:

                // Le joueur choisit de passer son tour
                break;

            case 3:

                affiche_menu();
                break;

            default:
                System.out.println("Choix invalide. Veuillez choisir une option valide.");
                break;
        }
    }
    
    private void placeTower() {

        boolean fin = true;
        System.out.println("Choisissez quelle type de tour vous voulez placer : (1) (2) (3) :");
        int choix_tours;

        while (fin) {

            choix_tours = scanner.nextInt();

            switch (choix_tours) {
            case 1:

                if(player.getMoney() >= 10){

                    System.out.println("Choisissez maintenant les coordonnés :");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    // Logique pour placer la tour à l'emplacement (x, y) sur la carte
                    map.placer(new Tower(20, 3, 2, y, x)); 
                    player.setMoney(player.getMoney() - 10);
                    fin = false;

                } else {

                    System.out.println("Vous n'avez pas assez d'argent");

                }
                
                break;

            case 2:

                if(player.getMoney() >= 20){

                    System.out.println("Choisissez maintenant les coordonnés :");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    // Logique pour placer la tour à l'emplacement (x, y) sur la carte
                    map.placer(new Tower(30, 2, 2, y, x)); 
                    player.setMoney(player.getMoney() - 20);
                    fin = false;

                } else {

                    System.out.println("Vous n'avez pas assez d'argent");

                }
                
                break;
        
            default:
                System.out.println("Veuillez selectionnez un nombre en .. et .."); //TODO a determiner selon le nombre de tour que l'on a créer
                break;
            }
            
        }
        
        
        scanner.nextLine(); // Pour consommer la nouvelle ligne
    
       
    }

}
