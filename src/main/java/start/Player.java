package src.main.java.start;

import src.main.java.UI.TerminalUI;

public class Player {

    private int money;
    private int lives;

    public Player(int money, int lives) {

        this.money = money;
        this.lives = lives;
        ajusterVieSelonDifficulte();

    }

    public void ajusterVieSelonDifficulte() {
        switch (TerminalUI.difficulté) {
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

    public void lostLive(){

        lives -= 1;

    }

    public void affiche() {

        System.out.println("Vous avez : " + money + " d'argent et " + lives + " vies");

    }

    public int getLives() {
        return lives;
    }

    public int getMoney() {
        return money;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}