package src.main.java.model;

import src.main.java.configMap.GameMap;

/**
 * Classe représentant une tour
 * Classe fille de Element
 * 
 * @see Element Pour avoir la liste des attributs et méthodes
 */
public class Tower extends Element {

    /** Prix et vitesse d'attaque */
    private int cost;
    private int attackSpeed;

    /**
     * Constructeur d'une tour
     * Il sera utilisé dans les classes qui représenteront les différentes tours
     * 
     * @param cost        Le prix d'une tour
     * @param attackSpeed La vitesse d'attaque d'une tour
     */
    public Tower(int health, int damage, int cost, int attackSpeed, int x, int y) {

        super(health, damage, x, y);
        this.attackSpeed = attackSpeed;
        this.cost = cost;

    }

    /* Getters et Setters de Cost */
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    /* Getters et Setters d'AttackSpeed */
    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Méthode permettant à une tour d'attaquer un ennemi
     * On cherche d'abord la position de l'ennemi sur la même ligne que la tour
     * On attaque ensuite le premier ennemi trouvé sur la même ligne
     * 
     * @param gameMap
     * @return true si l'attaque a réussi, false sinon
     */
    public boolean attack(GameMap gameMap) {

        Enemy enemy = gameMap.findEnemySameLine(this);

        if (enemy != null) {
            enemy.setHealth((enemy.health - damage) * attackSpeed);
            return true;
        }

        return false;

    }
}