package src.main.java.model;

public class Enemy extends Element {

    private int speed;

    public Enemy(int health, int damage, int speed) {

        super(health, damage);
        this.speed = speed;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Méthodes spécifiques à Enemy
    // ...
}