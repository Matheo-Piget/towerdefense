package src.main.java.model;

import src.main.java.configMap.GameMap;

public class Tower extends Element {

    private int attackSpeed;

    public Tower(int health, int damage, int attackSpeed, int x, int y) {

        super(health, damage, x, y);
        this.attackSpeed = attackSpeed;

    }

    public int getAttackSpeed() {
        return attackSpeed;
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