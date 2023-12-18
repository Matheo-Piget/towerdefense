package src.main.java.model;

import src.main.java.configMap.GameMap;

public class Enemy extends Element {

    private int speed;
    private boolean range;

    public Enemy(int health, int damage, int speed, int x, int y, boolean range) {

        super(health, damage, y, x);
        this.speed = speed;
        this.range = range;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean getRange(){

        return range;

    }

    public void attaque(GameMap map){

        Tower t = map.trouverTowerSurMemeLigne(this);

        if(getX() - t.getX() == 1) t.setHealth(health - getDamage());

    }

    public boolean attaque_loin(GameMap gameMap) {

        if(!range) return false;

        Tower tour = gameMap.trouverTowerSurMemeLigne(this); // Recherche de la tour sur la même ligne que l'enemi

        if (tour != null) {

            tour.setHealth(tour.health - damage); // Attaque la premiere tour trouvé sur la même ligne
            return true;

        }
        return false;

    }

    // Méthodes spécifiques à Enemy
    // ...
}