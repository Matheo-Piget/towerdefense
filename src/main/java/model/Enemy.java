package src.main.java.model;

import src.main.java.configMap.GameMap;

/**
 * Classe représentant un Ennemi
 * Classe fille de Element
 * 
 * @see Element Pour avoir la liste des attributs et méthodes
 */
public class Enemy extends Element {

    /** Vitesse de déplacement */
    private int speed;

    /**
     * Constructeur d'un ennemi
     * Il sera utilisé dans les classes qui représenteront les différents ennemis
     * 
     * @param speed La vitesse de déplacement d'un ennemi
     */
    public Enemy(int health, int damage, int speed, int x, int y) {
        super(health, damage, y, x);
        this.speed = speed;
    }

    /* Getter et Setter de Speed */
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Méthode permettant à un ennemi d'attaquer une tour
     * On cherche d'abord la position de la tour sur la même ligne que l'ennemi
     * On attaque ensuite la première tour trouvée sur la même ligne
     * 
     * @param gameMap La carte du jeu
     * @return true si l'attaque a réussi, false sinon
     */
    public boolean attaque(GameMap gameMap) {

        Tower tour = gameMap.findTowerSameLine(this);

        if (tour != null) {

            tour.setHealth(tour.health - damage);
            return true;

        }
        return false;

    }

}