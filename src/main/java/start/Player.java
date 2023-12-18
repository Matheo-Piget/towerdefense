package src.main.java.start;

public class Player {

    private int money;
    private int lives;

    public Player(int money, int lives) {

        this.money = money;
        this.lives = lives;

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