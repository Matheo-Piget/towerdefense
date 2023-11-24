public abstract class Element {

    protected int health;
    protected int damage;

    Element(int health, int damage){

        this.health = health;
        this.damage = damage;

    }

    // Méthodes communes à Tower et Enemy
    // ...
}