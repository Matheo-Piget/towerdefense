package src.main.java.model;

public class Tower extends Element {

    private int cost;
    private int attackSpeed;

    public Tower(int health, int damage, int cost, int attackSpeed) {

        super(health, damage);
        this.attackSpeed = attackSpeed;
        this.cost = cost;

    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    // Méthodes spécifiques à Tower
    // ...
}