package src.main.java.configMap;

import java.util.ArrayList;

import src.main.java.model.*;

/**
 * La classe GameMap représente la carte du jeu.
 * On pourra l'afficher, placer des éléments, etc.
 * 
 * @param tiles représente la carte du jeu sous forme de tableau de tableau avec
 *              dans chaque case une cellule
 * @see Cellule pour plus d'informations sur la cellule
 * @see Element pour plus d'informations sur les éléments
 */

public class GameMap {

    public Cellule[][] tiles;

    /**
     * On donne au constructeur de GameMap un nombre de lignes et de colonnes défini
     * et il se charge de construire la modélisation de la map
     * 
     * @param rows nombre de lignes
     * @param cols nombre de colonnes
     */
    public GameMap(int rows, int cols) {
        this.tiles = new Cellule[rows][cols];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = new Cellule();
            }
        }
    }

    /**
     * On cherche à récupérer la liste de tous les ennemis présents sur la carte
     * Dès que l'on croise un ennemi, on l'ajoute dans la liste
     * 
     * @param enemies est la liste des ennemis présents sur la carte
     * @return l'arraylist enemies
     */
    public ArrayList<Enemy> listOfEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getElement() instanceof Enemy)
                    enemies.add((Enemy) tiles[i][j].getElement());
            }
        }
        return enemies;
    }

    /**
     * On cherche à récupérer la liste de toutes les tours présentes sur la carte
     * Dès que l'on croise une tour, on l'ajoute dans la liste
     * 
     * @return Une arraylist contenant toutes les tours présentes sur la carte
     */
    public ArrayList<Tower> allTowers() {
        ArrayList<Tower> towers = new ArrayList<Tower>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getElement() instanceof Tower)
                    towers.add((Tower) tiles[i][j].getElement());
            }
        }
        return towers;
    }

    /** Méthode qui fait attaquer toutes les tours posées */
    public void towersAttack() {
        for (Tower t : allTowers()) {
            t.attack(this);
        }
    }

    /** Méthode qui fait attaquer tous les ennemis présents sur la carte */
    public void enemiesAttack() {
        for (Enemy t : listOfEnemies()) {
            t.attaque(this);
        }
    }

    /**
     * Méthode qui permet de placer un élément sur la carte
     * On regarde dans la condition que les coordonnées sont bien dans les limites
     * de la map et que la cellule est bien vide
     * 
     * @param e est l'élément à placer pris en argument par la fonction
     * @param x est la coordonnée x de l'élément
     * @param y est la coordonnée y de l'élément
     * 
     * @return true si l'élément a été placé, false sinon
     */

    public boolean putElem(Element e) {

        int x = e.getX();
        int y = e.getY();

        if (isInLimits(x, y) && !tiles[y][x].isOccupied()) {
            tiles[y][x].setElement(e);
            return true;
        }

        return false;

    }

    /**
     * Méthode qui permet de retirer un élément de la carte
     * On regarde dans la condition que les coordonnées sont bien dans les limites
     * de la map
     * Cette fois-ci, on affiche un message d'erreur si les coordonnées sont hors
     * limites
     * 
     * @param element est l'élément à retirer pris en argument par la fonction
     * @param x       est la coordonnée x de l'élément
     * @param y       est la coordonnée y de l'élément
     */

    public void removeElem(Element element) {

        int x = element.getX();
        int y = element.getY();

        if (isInLimits(x, y)) {
            tiles[y][x].setElement(null);
        } else {
            System.out.println("Coordonnées hors limites !");
        }

    }

    /**
     * Méthode qui permet de trouver une tour sur la même ligne qu'un ennemi
     * 
     * @param enemy est l'ennemi dont on cherche la tour sur la même ligne
     * @return la première tour trouvée sur la même ligne que l'ennemi
     *         null si aucune tour n'est trouvée
     */

    public Tower findTowerSameLine(Enemy enemy) {

        int enemyY = enemy.getY();

        for (int i = tiles[enemyY].length - 1; i >= 0; i--) {
            Element element = tiles[enemyY][i].getElement();
            if (element instanceof Tower) {
                return (Tower) element;
            }
        }

        return null;

    }

    /**
     * Méthode qui permet de trouver un ennemi sur la même ligne qu'une tour
     * 
     * @param tower est l'ennemi dont on cherche la tour sur la même ligne
     * @return la première tour trouvée sur la même ligne que l'ennemi
     *         null si aucune ennemi n'est trouvé
     */

    public Enemy findEnemySameLine(Tower tower) {

        int tourY = tower.getY();

        for (int y = 0; y < tiles[tourY].length; y++) {
            Element element = tiles[tourY][y].getElement();
            if (element instanceof Enemy) {
                return (Enemy) element;
            }
        }

        return null;
    }

    /**
     * Méthode qui permet de déplacer un élément sur la carte
     * Cette méthode nous servira pour réaliser le système de frame
     * On regarde dans la condition que les coordonnées sont bien dans les limites
     * de la map et que la cellule est bien vide
     * 
     * 
     * @param element l'élément à déplacer
     * @param newX    la nouvelle coordonnée x de l'élément
     * @param newY    la nouvelle coordonnée y de l'élément
     * @param oldX    l'ancienne coordonnée x de l'élément
     * @param oldY    l'ancienne coordonnée y de l'élément
     * @return true si l'élément a été déplacé, false s'il y a eu un problème
     */

    public boolean moveElem(Element element, int newX, int newY) {

        int oldX = element.getX();
        int oldY = element.getY();

        if (isInLimits(newX, newY)) {
            // Vérifie si la nouvelle position est libre
            if (!tiles[newY][newX].isOccupied()) {
                tiles[oldY][oldX].setElement(null);
                ; // Supprime l'élément de son ancienne position
                element.setX(newX);
                element.setY(newY);
                tiles[newY][newX].setElement(element);
                ; // Place l'élément à sa nouvelle position
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /** Retire tous les ennemis qui n'ont plus du tout de vie */
    public void removeDeadEnemies() {
        for (Enemy e : listOfEnemies()) {
            if (e.getHealth() <= 0)
                removeElem(e);
        }
    }

    /** Retire toutes les tours qui n'ont plus du tout de vie */
    public void removeDeadTowers() {
        for (Tower t : allTowers()) {
            if (t.getHealth() <= 0)
                removeElem(t);
        }
    }

    /**
     * Méthode permettant de mettre à jour la carte après un tour
     * Dans l'ordre, on fait :
     * Attaquer les tours - Attaquer les ennemis - Retirer les ennemis morts -
     * Retirer les tours mortes - Déplacer les ennemis - Faire apparaître de
     * nouveaux ennemis
     */

    public void update() {
        towersAttack();
        enemiesAttack();
        removeDeadEnemies(); // on supprime tout les enemis mort
        removeDeadTowers(); // meme chose pour les tours
        moveAllEnemies(); // on déplace tout les enemis
        putNewEnemies(); // TODO -> faire apparaître de nouveaux enemies suivant un timer ou autre
                         // alternative

    }

    /**
     * Méthode pour déplacer tous les ennemis en fonction de leur vitesse par frame
     */
    public void moveAllEnemies() {
        for (Enemy e : listOfEnemies()) {
            moveEnemy(e);
        }
    }

    /**
     * Méthode permettant de faire apparaître de nouveaux ennemis sur la carte après
     * une frame
     */

    public void putNewEnemies() {

        int mapWidth = tiles[0].length;
        int mapHeight = tiles.length;
        int numberOfEnemies = 2; // Nombre d'ennemis à placer (à ajuster selon vos besoins)

        for (int i = 0; i < numberOfEnemies; i++) {
            int randomX = mapWidth - 1; // Position aléatoire sur la dernière colonne de la carte
            int randomY = (int) (Math.random() * mapHeight); // Position aléatoire sur la hauteur de la carte

            putElem(new Enemy(1, 2, 1, randomX, randomY));
        }

    }

    /**
     * Méthode permettant de déplacer un ennemi précis
     * Dans la condition on vérifie qu'on reste dans les limites de la map
     * Si ce n'est pas le cas il faudra dans le futur adapter ça TODO
     * 
     * @param enemy        ennemi à déplacer
     * @param deplacementX le nombres de cases que l'ennemi va parcourir (varie en
     *                     fonction de l'enemi)
     * @param newX         la nouvelle coordonnée x de l'ennemi
     * 
     */
    private void moveEnemy(Enemy enemy) {

        int deplacementX = enemy.getSpeed();
        int newX = enemy.getX() - deplacementX;

        if (isInLimits(newX, enemy.getY())) {
            moveElem(enemy, newX, enemy.getY());
        } else {
            System.out.println("debug");
            // TODO: Gérer le comportement lorsque l'ennemi atteint les limites de la carte
        }
    }

    /**
     * Méthode permettant d'afficher la carte
     */
    public void showMap() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j].affiche();
            }
            System.out.println();
        }
    }

    /**
     * Méthode permettant de vérifier si les coordonnées sont dans les limites de la
     * carte
     * 
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si les coordonnées sont dans les limites de la carte, false
     */
    private boolean isInLimits(int x, int y) {

        return x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length; // teste si les coordenées sont dans les
                                                                            // limites

    }

}