package src.main.java.UI;

import java.util.Scanner;

import src.main.java.configMap.GameMap;
import src.main.java.model.towers.BulletTower;
import src.main.java.model.towers.SniperTower;
import src.main.java.model.towers.SpeedTower;
import src.main.java.model.towers.TntTower;
import src.main.java.model.towers.FigthTower;
import src.main.java.model.towers.NukeTower;
import src.main.java.start.Player;

public class TerminalUI {

    GameMap map;
    Player player;
    private final Scanner scanner;
    public static int difficulty;

    public TerminalUI(GameMap map, Player player) {

        this.map = map;
        this.player = player;
        difficulty = 1;
        this.scanner = new Scanner(System.in); // on initialise un champ scannaer pour gerer tout les choix via le
                                               // terminal de l'utitlisateur

    }

    public void affiche_règles() {

        System.out.println("\033\143");
        System.out.println("===== RÈGLES DU JEU =====");
        System.out.println("Le but du jeu est de survivre le plus longtemps possible.");
        System.out.println(
                "Pour cela, vous devez placer des tours sur la carte pour tuer les ennemis qui arrivent par la droite.");
        System.out.println(
                "Vous gagnez de l'argent en tuant des ennemis. Vous pouvez utiliser cet argent pour acheter des tours.");
        System.out.println("Vous perdez une vie à chaque fois qu'un ennemi atteint la fin de la carte.");

        System.out.println("===== TYPES DE TOURS =====");

        System.out.println("Name ==== Lives ==== Cost ==== Damage");
        System.out.println(" Fight Tower : 25, 40, 5");
        System.out.println(" Bullet Tower : 10, 50, 5");
        System.out.println(" Nuke Tower : 60, 200, 10");
        System.out.println(" Snioer Tower : 10, 80, 10");
        System.out.println(" Speed Tower : 15, 75, 10");
        System.out.println(" Tnt Tower : 1, 220, 70");

        System.out.println("===== TYPES D'ENNEMIS =====");

        System.out.println("Name ==== Lives ==== Damage");
        System.out.println(" Dreth : 10, 10");
        System.out.println("  Fyron : 20, 10");
        System.out.println(" Gazer : 10, 5");
        System.out.println(" Kryon : 70, 30");
        System.out.println(" Liche : 50, 20");
        System.out.println(" Zorch : 10, 2");

        System.out.println("Insérez (M) pour revenir au menu principal.");

        String choix = scanner.nextLine();
        if (choix.equalsIgnoreCase("M")) {
            System.out.println("\033\143");
            affiche_menu();
        } else {
            System.out.println("Choix invalide. Veuillez choisir une option valide.");
        }

    }

    public void affiche_menu() {
        int choix;

        System.out.println("===== MENU DU JEU =====");
        System.out.println("1. Démarrer le jeu");
        System.out.println("2. Reprendre le jeu");
        System.out.println("3. Options");
        System.out.println("4. Règles");
        System.out.println("5. Scores");
        System.out.println("6. Quitter");

        System.out.print("Entrez votre choix : "); // pour choisir ce que l'on veut faire
        choix = scanner.nextInt();

        switch (choix) {
            case 1:
                System.out.println("\033\143");
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
                affiche_règles();
                break;
            case 5:
                System.out.println("Scores du jeu");
                break;
            case 6:
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

        difficulty = choixDifficulte;

        System.out.println("Difficulté réglée avec succès !");
        affiche_menu(); // Revenir au menu principal
    }

    public void startTerminalGame(GameMap map, Player player) {

        affiche_menu(); // au démarrage on affiche le menu

    }

    private void start() {
        boolean gameOver = false;
        int frame = 1;

        while (!gameOver) {
            // Mettre à jour la carte
            player.setMoney(map.update(player) + player.getMoney());

            // Afficher les informations du joueur et la carte
            player.affiche();// Méthode à créer dans la classe Player pour afficher les infos
            map.affiche(); // Méthode à créer dans la classe GameMap pour afficher la carte

            System.out.println("Frame : " + frame++);

            // Afficher le menu pour les actions du joueur pendant le jeu
            displayInGameMenu();

            gameOver = player.getLives() <= 0;

        }

        System.out.println("Game over !");
        System.out.println("Voulez vous relancer une partie ? ");
        String reponse = scanner.nextLine();

        switch (reponse) {
            case "oui":
                System.out.println("\033\143");
                TerminalUI new_partie = new TerminalUI(new GameMap(5, 10), new Player(200, 10));
                new_partie.start();

                break;

            case "non":

                System.out.println("Très bien, au revoir");
                System.exit(0);
                break;

            default:
                System.exit(0);
                break;
        }

    }

    private void displayInGameMenu() {
        int choice;

        do {

            System.out.println("===== MENU D'ACTION =====");
            System.out.println("1. Placer une tour");
            System.out.println("2. Fin du tour");
            System.out.println("3. Retour au Menu");

            System.out.print("Entrez votre choix : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choice) {

                case 1:

                    placeTower();
                    break;

                case 2:
                    System.out.println("\033\143");
                    break;

                case 3:
                    System.out.println("\033\143");
                    affiche_menu();
                    break;

                default:
                    System.out.println("\033\143");
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
                    break;
            }

        } while (choice == 1 || choice == 3);

    }

    private void placeTower() {

        boolean fin = true;
        System.out.println(
                "Choisissez quelle type de tour vous voulez placer : (1) Fight Tower,  (2) Bullet Tower, (3) Nuke tower, (4) Sniper Tower, (5) Speed Tower, (6) Tnt Tower :");
        int choix_tours;

        while (fin) {

            choix_tours = scanner.nextInt();

            switch (choix_tours) {
                case 1:

                    if (player.getMoney() >= 40) {

                        System.out.println("Choisissez maintenant les coordonnées :");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        // Logique pour placer la tour à l'emplacement (x, y) sur la carte
                        map.putElem(new FigthTower(y, x));
                        player.setMoney(player.getMoney() - 40);
                        fin = false;

                    } else {

                        System.out.println("Vous n'avez pas assez d'argent");

                    }

                    break;

                case 2:

                    if (player.getMoney() >= 50) {

                        System.out.println("Choisissez maintenant les coordonnées :");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        // Logique pour placer la tour à l'emplacement (x, y) sur la carte
                        map.putElem(new BulletTower(y, x));
                        player.setMoney(player.getMoney() - 50);
                        fin = false;

                    } else {

                        System.out.println("Vous n'avez pas assez d'argent");

                    }

                    break;

                case 3:

                    if (player.getMoney() >= 300) {

                        System.out.println("Choisissez maintenant les coordonnées :");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        // Logique pour placer la tour à l'emplacement (x, y) sur la carte
                        map.putElem(new NukeTower(y, x));
                        player.setMoney(player.getMoney() - 80);
                        fin = false;

                    } else {

                        System.out.println("Vous n'avez pas assez d'argent");

                    }

                    break;

                case 4:

                    if (player.getMoney() >= 80) {

                        System.out.println("Choisissez maintenant les coordonnées :");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        // Logique pour placer la tour à l'emplacement (x, y) sur la carte
                        map.putElem(new SniperTower(y, x));
                        player.setMoney(player.getMoney() - 80);
                        fin = false;

                    } else {

                        System.out.println("Vous n'avez pas assez d'argent");

                    }

                    break;

                case 5:

                    if (player.getMoney() >= 75) {

                        System.out.println("Choisissez maintenant les coordonnées :");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        // Logique pour placer la tour à l'emplacement (x, y) sur la carte
                        map.putElem(new SpeedTower(y, x));
                        player.setMoney(player.getMoney() - 75);
                        fin = false;

                    } else {

                        System.out.println("Vous n'avez pas assez d'argent");

                    }

                    break;

                case 6:

                    if (player.getMoney() >= 220) {

                        System.out.println("Choisissez maintenant les coordonnées :");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        // Logique pour placer la tour à l'emplacement (x, y) sur la carte
                        map.putElem(new TntTower(y, x));
                        player.setMoney(player.getMoney() - 220);
                        fin = false;

                    } else {

                        System.out.println("Vous n'avez pas assez d'argent");

                    }

                    break;

                default:
                    System.out.println("Veuillez selectionnez un nombre en 1 et 6 : ");
                    break;
            }

        }

        scanner.nextLine(); // Pour consommer la nouvelle ligne

    }

}
