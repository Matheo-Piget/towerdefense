package src.main.java.model;

import src.main.java.configMap.GameMap;

public class Tower extends Element {

    private int cost;
    private int attackSpeed;

    public Tower(int health, int damage, int cost, int attackSpeed, int x, int y) {

        super(health, damage, x, y);
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

    public boolean attaque(GameMap gameMap) {

        Enemy enemy = gameMap.trouverEnnemiSurMemeLigne(this); // Recherche de l'ennemi sur la même ligne que la tour

        if (enemy != null) {

            enemy.setHealth((enemy.health - damage)*attackSpeed); // Attaque le premier ennemi trouvé sur la même ligne
            return true;

        } 

        return false;

    }

    // Méthodes spécifiques à Tower
    // ...
}