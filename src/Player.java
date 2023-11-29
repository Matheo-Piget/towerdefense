package src;

public class Player {

    private int money;
    private int lives;

    public Player(int money, int lives) {

        this.money = money;
        this.lives = lives;

    }

    public void affiche() {

        System.out.println("Vous avez : " + money + " d'argent et " + lives + "vies");

    }

    // Getters, setters, and methods for managing money, lives, etc.
}