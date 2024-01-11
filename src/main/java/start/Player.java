package src.main.java.start;

import src.main.java.UI.TerminalUI;
import src.main.java.configMap.GameMap;

/**
 * Classe représentant le joueur
 * Cette classe est uniquement présente pour modéliser le joueur et ses
 * capacités d'action sur le jeu
 */
public class Player {

    /* Argent vie et score */
    private int money;
    private int lives;
    public int score;

    /**
     * Constructeur de la classe Player
     * 
     * @param money Argent du joueur
     * @param lives Vies du joueur
     */

    public Player(int money, int lives) {

        this.money = money;
        this.lives = lives;
        ajustLifeAccordingDifficulty();

    }

    /** On ajuste le nombre de vie en fonction de la difficulté */
    public void ajustLifeAccordingDifficulty() {
        switch (GameMap.difficulty) {
            case 1: // Facile
                setLives(10);
                break;
            case 2: // Moyen
                setLives(5);
                break;
            case 3: // Difficile
                setLives(3);
                break;
            default:
                setLives(10); // Valeur par défaut pour éviter les erreurs
                break;
        }
    }

    /** Lorsqu'on perd une vie */
    public void lostLive() {

        lives -= 1;

    }

    public boolean gameOver() {

        return getLives() <= 0;

    }

    public void reset() {

        lives = 10;
        money = 700;
        score = 0;

    }

    /** Affichage des caractéristiques du joueur */

    public void affiche() {

        System.out.println(
                "\u001B[32m" + "Argent : " + money + " $" + "\u001B[31m" + "          Vies :  " + lives + " ❤"
                        + "\u001B[0m");

    }

    /* Getter et Setter de Lives */
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    /* Getter et Setter de Money */
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}