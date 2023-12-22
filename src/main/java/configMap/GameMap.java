package src.main.java.configMap;

import java.util.ArrayList;
import java.util.Random;

import src.main.java.UI.TerminalUI;
import src.main.java.model.*;
import src.main.java.start.Player;

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
     * Fonction permettant de calculer combien un ennemi nous rapporte s'il meurt
     */
    public int calculerGainEnnemiMort(int choix_enemie) {
        int gain = 0;
        switch (TerminalUI.difficulté) {
            case 1: // Facile
                switch (choix_enemie) {
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
            case 2: // Moyen
                switch (choix_enemie) {
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
            case 3: // Difficile
                switch (choix_enemie) {
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
     * @return l'arraylist enemies
     */
    public ArrayList<Enemy> tout_les_enemy() { // retourne tous les enemies sous forme d'une liste, mieux manipulable

        ArrayList<Enemy> enemys = new ArrayList<Enemy>();

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                if (tiles[i][j].get_elt() instanceof Enemy)
                    enemys.add((Enemy) tiles[i][j].get_elt()); // des que l'on a un enmis ou l'ajoute dasn la liste

            }
        }

        return enemys;

    }

    /**
     * On cherche à récupérer la liste de toutes les tours présentes sur la carte
     * Dès que l'on croise une tour, on l'ajoute dans la liste
     * 
     * @return Une arraylist contenant toutes les tours présentes sur la carte
     */
    public ArrayList<Tower> tout_les_tower() { // retourne tous les tours sous forme d'une liste, mieux manipulable

        ArrayList<Tower> towers = new ArrayList<Tower>();

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                if (tiles[i][j].get_elt() instanceof Tower)
                    towers.add((Tower) tiles[i][j].get_elt()); // des que l'on a une tours ou l'ajoute dasn la liste

            }
        }

        return towers;

    }

    /** Méthode qui fait attaquer toutes les tours posées */
    public void tours_attaque() { // fonction qui fait attaquer toutes les tours

        for (Tower t : tout_les_tower()) {

            t.attaque(this);

        }

    }

    /** Méthode qui fait attaquer tous les ennemis présents sur la carte */
    public void enemies_attaque(Player p) { // fonction qui fait attaquer toutes les enemis

        for (Enemy t : tout_les_enemy()) {

            if (t.getRange())
                t.attaque_loin(this, p);
            else
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
    public boolean placer(Element e) {

        int x = e.getX();
        int y = e.getY();

        if (estDansLimites(x, y) && !tiles[y][x].isOccupied()) { // on regarde si les coordnonnées sont dans les limites
                                                                 // et que la cellule en question est vide

            tiles[y][x].set_elt(e);
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
    public void retirerElement(Element element) {

        int x = element.getX();
        int y = element.getY();

        if (estDansLimites(x, y)) {
            tiles[y][x].set_elt(null);
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
    public Tower trouverTowerSurMemeLigne(Enemy e) {

        int enemyY = e.getY();

        for (int i = tiles[enemyY].length - 1; i >= 0; i--) {

            Element element = tiles[enemyY][i].get_elt();

            if (element instanceof Tower) {

                return (Tower) element; // Retourne la premiere tour trouvé sur la même ligne que l'enemi

            }
        }

        return null; // Aucune tour sur la même ligne que la tour

    }

    /**
     * Méthode qui permet de trouver un ennemi sur la même ligne qu'une tour
     * 
     * @param tower est l'ennemi dont on cherche la tour sur la même ligne
     * @return la première tour trouvée sur la même ligne que l'ennemi
     *         null si aucune ennemi n'est trouvé
     */
    public Enemy trouverEnnemiSurMemeLigne(Tower tower) {

        int tourY = tower.getY();

        for (int y = 0; y < tiles[tourY].length; y++) {

            Element element = tiles[tourY][y].get_elt();

            if (element instanceof Enemy) {

                return (Enemy) element; // Retourne le premier ennemi trouvé sur la même ligne que la tour

            }
        }

        return null; // Aucun ennemi sur la même ligne que la tour
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
    public boolean deplacerElement(Element element, int newX, int newY) {

        int oldX = element.getX();
        int oldY = element.getY();

        if (estDansLimites(newX, newY)) {

            // Vérifie si la nouvelle position est libre
            if (!tiles[newY][newX].isOccupied()) {

                tiles[oldY][oldX].set_elt(null);
                ; // Supprime l'élément de son ancienne position
                element.setX(newX);
                element.setY(newY);
                tiles[newY][newX].set_elt(element);
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
    public int enemyMort() { // supprime tout les enemies qui n'ont plus de vie

        int money_win = 0;

        for (Enemy e : tout_les_enemy()) {

            if (e.getHealth() <= 0) {

                money_win += calculerGainEnnemiMort(type_e(e));
                retirerElement(e);
            }

        }

        return money_win;

    }

    private int type_e(Enemy e) {
        if (e instanceof WeakEnemy)
            return 1;
        if (e instanceof MediumEnemy)
            return 2;
        if (e instanceof RangeEnemy)
            return 3;
        else
            return 4;
    }

    /** Retire toutes les tours qui n'ont plus du tout de vie */
    public void towerMorte() { // supprime tout les tours qui n'ont plus de vie

        for (Tower t : tout_les_tower()) {

            if (t.getHealth() <= 0)
                retirerElement(t);

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

        tours_attaque();
        enemies_attaque(p);
        int money_win = enemyMort(); // on supprime tout les enemis mort
        towerMorte(); // meme chose pour les tours
        deplacerTousLesEnnemis(); // on déplace tout les enemis
        nouveauxEnemy();

        return money_win;

    }

    /**
     * Méthode pour déplacer tous les ennemis en fonction de leur vitesse par frame
     */
    public void deplacerTousLesEnnemis() {

        for (Enemy e : tout_les_enemy()) {

            deplacerEnnemi(e);

        }

    }

    /**
     * Méthode permettant de faire apparaître de nouveaux ennemis sur la carte après
     * une frame
     */
    public void nouveauxEnemy() {

        int mapHeight = tiles.length;
        int numberOfEnemies = 2; // Nombre d'ennemis à placer (à ajuster selon vos besoins)
        Random r = new Random();
        int random_enemies = (int) (Math.random() * 4);

        for (int i = 0; i < numberOfEnemies; i++) {
            int randomX = r.nextBoolean() ? 8 : 9;// Position aléatoire sur la dernière colonne de la carte
            int randomY = (int) (Math.random() * mapHeight); // Position aléatoire sur la hauteur de la carte

            switch (random_enemies) { // place un enemie alétoire parmit tout les types d'enemies possible
                case 0:
                    placer(new WeakEnemy(randomX, randomY));
                    break;

                case 1:
                    placer(new MediumEnemy(randomX, randomY));
                    break;
                case 2:
                    placer(new RangeEnemy(randomX, randomY));
                    break;
                case 3:
                    placer(new StrongEnemy(randomX, randomY));
                    break;

                default:
                    placer(new WeakEnemy(randomX, randomY));
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
    private void deplacerEnnemi(Enemy enemy) {

        // Logique pour calculer les déplacements en fonction de la vitesse de l'ennemi
        int deplacementX = enemy.getSpeed();

        // Calcul des nouvelles positions
        int newX = enemy.getX() - deplacementX;

        // Vérification si les nouvelles positions sont à l'intérieur des limites de la
        // carte
        if (estDansLimites(newX, enemy.getY())) {

            deplacerElement(enemy, newX, enemy.getY());// Déplacement de l'ennemi aux nouvelles positions

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

    /**
     * Méthode permettant de vérifier si les coordonnées sont dans les limites de la
     * carte
     * 
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si les coordonnées sont dans les limites de la carte, false
     */
    private boolean estDansLimites(int x, int y) {

        return x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length; // teste si les coordenées sont dans les
                                                                            // limites

    }

    public int getRows() {
        return tiles.length;
    }

    public int getCols() {
        return tiles[0].length;
    }

    // Methods for interacting with tiles, placing towers, moving enemies, etc.
}