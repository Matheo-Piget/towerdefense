package src.main.java.model;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

/**
 * Classe représentant un Ennemi
 * Classe fille de Element
 * 
 * @see Element Pour avoir la liste des attributs et méthodes
 */

public class Enemy extends Element {

    /** Vitesse de déplacement et portée d'un Ennemi */
    private int speed;
    private boolean range;

    /**
     * Constructeur d'un ennemi
     * Il sera utilisé dans les classes qui représenteront les différents ennemis
     * 
     * @param speed La vitesse de déplacement d'un ennemi
     */
    public Enemy(int health, int damage, int speed, int x, int y, boolean range) {

        super(health, damage, y, x);
        this.speed = speed;
        this.range = range;

    }

    /* Getter et Setter de Speed */

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /* Getter de Range */
    public boolean getRange() {

        return range;

    }

    /**
     * Méthode permettant à un ennemi d'attaquer une tour
     * On cherche d'abord la position de la tour sur la même ligne que l'ennemi
     * On attaque ensuite la première tour trouvée sur la même ligne
     * 
     * @param gameMap La carte du jeu
     * @return true si l'attaque a réussi, false sinon
     */
    public void attack(GameMap map, Player p) {

        Tower t = map.findTowerOnSameLine(this);

        if (getX() == 0)
            p.lostLive();
        if (t == null)
            return;
        else if (getX() - t.getX() == 1)
            t.setHealth(health - getDamage());

    }

    public void rangeAttack(GameMap gameMap, Player p) {

        Tower tour = gameMap.findTowerOnSameLine(this); // Recherche de la tour sur la même ligne que l'enemi

        if (tour != null) {

            tour.setHealth(tour.health - damage); // Attaque la premiere tour trouvé sur la même ligne

        }
        p.lostLive();

    }

    // Méthodes spécifiques à Enemy
    // ...
}