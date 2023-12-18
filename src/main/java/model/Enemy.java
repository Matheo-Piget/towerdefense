package src.main.java.model;

import src.main.java.configMap.GameMap;

public class Enemy extends Element {

    private int speed;

    public Enemy(int health, int damage, int speed, int x, int y) {

        super(health, damage, y, x);
        this.speed = speed;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean attaque(GameMap gameMap) {

        Tower tour = gameMap.findTowerSameLine(this); // Recherche de la tour sur la même ligne que l'enemi

        if (tour != null) {

            tour.setHealth(tour.health - damage); // Attaque la premiere tour trouvé sur la même ligne
            return true;

        }
        return false;

    }

    // Méthodes spécifiques à Enemy
    // ...
}