package src.main.java.model;

/**
 * Classe abstraite représentant un élément du jeu
 * Classe mère de Tower et Enemy
 */

public abstract class Element {

    /** Vie, dégats et position d'un élément */
    protected int health;
    protected int damage;
    protected int x, y;

    /**
     * Constructeur d'un élément
     * Il sera appelé dans les classes filles avec super()
     * 
     * @param health La vie
     * @param damage Les dégats
     * @param x      La position en x
     * @param y      La position en y
     */

    Element(int health, int damage, int y, int x) {

        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;

    }

    /* Getter et Setter de Damage */
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    /* Getter et Setter de X */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    /* Getter et Setter de Y */

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /* Getter et Setter de Vie */

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}