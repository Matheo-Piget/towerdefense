package src.main.java.model;

public abstract class Element {

    protected int health;
    protected int damage;
    protected int x, y;

    Element(int health, int damage, int x, int y) {

        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        

    }

    public int getDamage() {
        return damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    } 
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
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