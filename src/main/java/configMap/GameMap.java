package src.main.java.configMap;

import java.util.ArrayList;
import java.util.Random;

import src.main.java.UI.TerminalUI;
import src.main.java.model.*;
import src.main.java.model.enemies.Dreth;
import src.main.java.model.enemies.Fyron;
import src.main.java.model.enemies.Gazer;
import src.main.java.model.enemies.Kryon;
import src.main.java.model.enemies.Liche;
import src.main.java.model.enemies.Zorch;
import src.main.java.start.Player;

/**
 * La classe GameMap représente la carte du jeu.
 * On pourra l'afficher, placer des éléments, etc.
 * 
 * @param tiles représente la carte du jeu sous forme de tableau de tableau avec
 *              dans chaque case une cellule
 * @see Cellule pour plus d'informations sur la cellule
 * @see Element pour plus d'informations sur les éléments (classe contenant les
 *      deux enfants importants : Tower et Enemy)
 */

public class GameMap {

    /** Tableau représentant la carte du jeu */
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

    public int getRows() {
        return tiles.length;
    }

    public int getCols() {
        return tiles[0].length;
    }

    /**
     * Retourne le type d'un ennemi sous forme d'un entier pour savoir de quel
     * ennemi on parle
     */
    private int typeE(Enemy e) {
        if (e instanceof Kryon)
            return 1;
        if (e instanceof Dreth)
            return 2;
        if (e instanceof Fyron)
            return 3;
        else
            return 4;
    }

    /**
     * Fonction permettant de calculer combien un ennemi nous rapporte s'il meurt
     * case 1 : pour le niveau facile
     * case 2 : pour le niveau moyen
     * case 3 : pour le niveau difficile
     * TODO A MODIFIER POUR EQUILIBRER LE JEU
     */
    public int moneyOfDeadEnemy(int choiceEnemy) {
        int gain = 0;
        switch (TerminalUI.difficulty) {
            case 1:
                switch (choiceEnemy) {
                    case 1:
                        gain = 3;
                        break;
                    case 2:
                        gain = 4;
                        break;
                    case 3:
                        gain = 5;
                        break;
                    case 4:
                        gain = 5;
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (choiceEnemy) {
                    case 1:
                        gain = 2;
                        break;
                    case 2:
                        gain = 3;
                        break;
                    case 3:
                        gain = 4;
                        break;
                    case 4:
                        gain = 4;
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (choiceEnemy) {
                    case 1:
                        gain = 1;
                        break;
                    case 2:
                        gain = 2;
                        break;
                    case 3:
                        gain = 3;
                        break;
                    case 4:
                        gain = 3;
                        break;
                    default:
                        break;
                }
                break;
            default:
                gain = 5; // Valeur par défaut pour éviter les erreurs
                break;
        }
        return gain;
    }

    /**
     * On cherche à récupérer la liste de tous les ennemis présents sur la carte
     * Dès que l'on croise un ennemi, on l'ajoute dans la liste
     * 
     * @param enemies est la liste des ennemis présents sur la carte
     * @return l'arraylist contenant les ennemis
     */
    public ArrayList<Enemy> listOfAllEnemies() {
        ArrayList<Enemy> list = new ArrayList<Enemy>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getElem() instanceof Enemy)
                    list.add((Enemy) tiles[i][j].getElem());
            }
        }
        return list;
    }

    /**
     * On cherche à récupérer la liste de toutes les tours présentes sur la carte
     * Dès que l'on croise une tour, on l'ajoute dans la liste
     * 
     * @return Une arraylist contenant toutes les tours présentes sur la carte
     */
    public ArrayList<Tower> listOfAllTowers() {

        ArrayList<Tower> list = new ArrayList<Tower>();

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                if (tiles[i][j].getElem() instanceof Tower)
                    list.add((Tower) tiles[i][j].getElem());

            }
        }

        return list;

    }

    /**
     * Méthode permettant de vérifier si les coordonnées sont dans les limites de la
     * carte
     * 
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si les coordonnées sont dans les limites de la carte, false
     */
    private boolean isWithinLimits(int x, int y) {

        return x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length; // teste si les coordenées sont dans les
                                                                            // limites

    }

    /** Méthode qui fait attaquer toutes les tours posées */
    public void attackTowers() { // fonction qui fait attaquer toutes les tours

        for (Tower t : listOfAllTowers()) {
            t.attaque(this);
        }

    }

    /**
     * Méthode qui fait attaquer tous les ennemis présents sur la carte
     * Nuance qui vérifie si l'ennemi est à distance ou non
     */
    public void attackEnemies(Player p) {

        for (Enemy t : listOfAllEnemies()) {
            t.attaque(this, p);
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
    public boolean putElem(Element elem) {

        int x = elem.getX();
        int y = elem.getY();

        if (isWithinLimits(x, y) && !tiles[y][x].isOccupied()) {
            tiles[y][x].setElem(elem);
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

        if (isWithinLimits(x, y)) {
            tiles[y][x].setElem(null);
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
    public Tower findTowerOnSameLine(Enemy enemy) {

        int enemyY = enemy.getY();

        for (int i = tiles[enemyY].length - 1; i >= 0; i--) {
            Element element = tiles[enemyY][i].getElem();

            if (element instanceof Tower) {
                return ((Tower) element);
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
    public Enemy findEnemyOnSameLine(Tower tower) {

        int tourY = tower.getY();

        for (int y = 0; y < tiles[tourY].length; y++) {
            Element element = tiles[tourY][y].getElem();

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

        if (isWithinLimits(newX, newY)) {

            // Vérifie si la nouvelle position est libre
            if (!tiles[newY][newX].isOccupied()) {

                tiles[oldY][oldX].setElem(null);
                ; // Supprime l'élément de son ancienne position
                element.setX(newX);
                element.setY(newY);
                tiles[newY][newX].setElem(element);
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
    public int removeDeadEnemies() { // supprime tout les enemies qui n'ont plus de vie

        int money_win = 0;

        for (Enemy e : listOfAllEnemies()) {

            if (e.getHealth() <= 0) {
                money_win += moneyOfDeadEnemy(typeE(e));
                removeElem(e);
            }

        }

        return money_win;

    }

    /** Retire toutes les tours qui n'ont plus du tout de vie */
    public void removeDeadTowers() { // supprime tout les tours qui n'ont plus de vie

        for (Tower t : listOfAllTowers()) {

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
    public int update(Player p) {

        attackTowers();
        attackEnemies(p);
        int money_win = removeDeadEnemies(); // on supprime tout les enemis mort
        removeDeadTowers(); // meme chose pour les tours
        moveAllEnemies(); // on déplace tout les enemis
        spawnNewEnemies();

        return money_win;

    }

    /**
     * Méthode pour déplacer tous les ennemis en fonction de leur vitesse par frame
     */
    public void moveAllEnemies() {

        for (Enemy e : listOfAllEnemies()) {
            moveSpecificEnemy(e);
        }

    }

    /**
     * Méthode permettant de faire apparaître de nouveaux ennemis sur la carte après
     * une frame
     */
    public void spawnNewEnemies() {

        int mapHeight = tiles.length;
        int numberOfEnemies = 2; // Nombre d'ennemis à placer 
        Random r = new Random();
        int random_enemies;

        for (int i = 0; i < numberOfEnemies; i++) {
            int randomX = r.nextBoolean() ? 8 : 9;// Position aléatoire sur la dernière colonne de la carte
            int randomY = (int) (Math.random() * mapHeight); // Position aléatoire sur la hauteur de la carte

            random_enemies = (int) (Math.random() * 6);

            switch (random_enemies) { // place un enemie alétoire parmit tout les types d'enemies possible
                case 0:
                    putElem(new Kryon(randomX, randomY));
                    break;

                case 1:
                    putElem(new Dreth(randomX, randomY));
                    break;
                case 2:
                    putElem(new Fyron(randomX, randomY));
                    break;
                case 3:
                    putElem(new Gazer(randomX, randomY));
                    break;

                case 4:
                    putElem(new Liche(randomX, randomY));
                    break;

                case 5:
                    putElem(new Zorch(randomX, randomY));
                    break;

                default:
                    putElem(new Kryon(randomX, randomY));
                    break;
            }

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
    private void moveSpecificEnemy(Enemy enemy) {

        // Logique pour calculer les déplacements en fonction de la vitesse de l'ennemi
        int deplacementX = enemy.getSpeed();

        // Calcul des nouvelles positions
        int newX = enemy.getX() - deplacementX;

        // Vérification si les nouvelles positions sont à l'intérieur des limites de la
        // carte
        if (isWithinLimits(newX, enemy.getY())) {

            moveElem(enemy, newX, enemy.getY());// Déplacement de l'ennemi aux nouvelles positions

        }
    }

    /**
     * Méthode permettant d'afficher la carte
     */
    public void affiche() {

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                tiles[i][j].affiche();

            }

            System.out.println();

        }

    }

    public void reset() {

        for(Enemy e : listOfAllEnemies()) removeElem(e);
        for (Tower t : listOfAllTowers()) removeElem(t);
        

    }

}