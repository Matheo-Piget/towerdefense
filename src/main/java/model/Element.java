package src.main.java.model;

public abstract class Element {

    protected int health;
    protected int damage;

    Element(int health, int damage) {

        this.health = health;
        this.damage = damage;

    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Méthodes communes à Tower et Enemy
    // ...
}