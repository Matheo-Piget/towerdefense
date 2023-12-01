package src.main.java.model;

public class Tower extends Element {

    private int cost;
    private int attackSpeed;

    public Tower(int health, int damage, int cost, int attackSpeed) {

        super(health, damage);
        this.attackSpeed = attackSpeed;
        this.cost = cost;

    }

    // Méthodes spécifiques à Tower
    // ...
}