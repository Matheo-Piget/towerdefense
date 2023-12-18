package src.main.java.configMap;

import java.util.ArrayList;

import src.main.java.model.*;
import src.main.java.start.Player;

public class GameMap {

    public Cellule[][] tiles;

    public GameMap(int rows, int cols) {

        this.tiles = new Cellule[rows][cols];

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                tiles[i][j] = new Cellule();

            }
        }
    }

    public ArrayList<Enemy> tout_les_enemy(){ // retourne tous les enemies sous forme d'une liste, mieux manipulable

        ArrayList<Enemy> enemys = new ArrayList<Enemy>();

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                if(tiles[i][j].get_elt() instanceof Enemy) enemys.add((Enemy) tiles[i][j].get_elt()); // des que l'on a un enmis ou l'ajoute dasn la liste

                
            }
        }

        return enemys;


    }

    public ArrayList<Tower> tout_les_tower(){ // retourne tous les tours sous forme d'une liste, mieux manipulable

        ArrayList<Tower> towers = new ArrayList<Tower>();

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                if(tiles[i][j].get_elt() instanceof Tower) towers.add((Tower) tiles[i][j].get_elt()); // des que l'on a une tours ou l'ajoute dasn la liste

                
            }
        }

        return towers;


    }

    public void tours_attaque(){ // fonction qui fait attaquer toutes les tours

        for (Tower t : tout_les_tower()) {
            
            t.attaque(this);

        }

    }

    public void enemies_attaque(Player p){ // fonction qui fait attaquer toutes les enemis

        for (Enemy t : tout_les_enemy()) {
            
            if(!t.attaque(this)) p.lostLive();

        }

    }

    public boolean placer(Element e) {

        int x = e.getX();
        int y = e.getY();

        if (estDansLimites(x, y) && !tiles[y][x].isOccupied()) { // on regarde si les coordnonnées sont dans les limites et que la cellule en question est vide

            tiles[y][x].set_elt(e);
            return true; 

        }
        
        return false;

    }

    public void retirerElement(Element element) {

        int x = element.getX();
        int y = element.getY();

        if (estDansLimites(x, y)) {
            tiles[y][x].set_elt(null);
        } else {
            System.out.println("Coordonnées hors limites !");
        }
    }

    public Tower trouverTowerSurMemeLigne(Enemy e){

        int enemyY = e.getY();

        for (int i = tiles[enemyY].length - 1; i >= 0; i--) {

            Element element = tiles[enemyY][i].get_elt();

            if (element instanceof Tower) {

                return (Tower) element; // Retourne la premiere tour trouvé sur la même ligne que l'enemi

            }
        }

        return null; // Aucune tour sur la même ligne que la tour


    }

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

    public boolean deplacerElement(Element element, int newX, int newY) {

        int oldX = element.getX();
        int oldY = element.getY();

        if (estDansLimites(newX, newY)) {

            // Vérifie si la nouvelle position est libre
            if (!tiles[newY][newX].isOccupied()) {

                tiles[oldY][oldX].set_elt(null);; // Supprime l'élément de son ancienne position
                element.setX(newX);
                element.setY(newY);
                tiles[newY][newX].set_elt(element);; // Place l'élément à sa nouvelle position
                return true;

            } else {

                return false;

            }

        } else {

            return false;

        }
    }

    public int enemyMort(){ // supprime tout les enemies qui n'ont plus de vie

        int money_win = 0;

        for (Enemy e : tout_les_enemy()) {
            
            if(e.getHealth() <= 0) retirerElement(e); money_win += 10;

        }

        return money_win;

    }

    public void towerMorte(){ // supprime tout les tours qui n'ont plus de vie

        for (Tower t : tout_les_tower()) {
            
            if(t.getHealth() <= 0) retirerElement(t);

        }

    }

    public int update(Player p){

        tours_attaque();
        enemies_attaque(p);
        int money_win = enemyMort(); // on supprime tout les enemis mort
        towerMorte(); // meme chose pour les tours
        deplacerTousLesEnnemis(); // on déplace tout les enemis
        nouveauxEnemy();

        return money_win;

        

    }

    // Méthode pour déplacer tous les ennemis en fonction de leur vitesse
    public void deplacerTousLesEnnemis() {

        for (Enemy e : tout_les_enemy()) {
            
            deplacerEnnemi(e);

        }

    }

    public void nouveauxEnemy(){

        int mapWidth = tiles[0].length;
        int mapHeight = tiles.length;
        int numberOfEnemies = 2; // Nombre d'ennemis à placer (à ajuster selon vos besoins)
    
        for (int i = 0; i < numberOfEnemies; i++) {
            int randomX = mapWidth - 1; // Position aléatoire sur la dernière colonne de la carte
            int randomY = (int) (Math.random() * mapHeight); // Position aléatoire sur la hauteur de la carte
    
            placer(new Enemy(1, 2, 1, randomX, randomY)); 
        }

    }

    // Méthode pour déplacer un ennemi spécifique
    private void deplacerEnnemi(Enemy enemy) {

        // Logique pour calculer les déplacements en fonction de la vitesse de l'ennemi
        int deplacementX = enemy.getSpeed();

        // Calcul des nouvelles positions
        int newX = enemy.getX() - deplacementX;

        // Vérification si les nouvelles positions sont à l'intérieur des limites de la carte
        if (estDansLimites(newX, enemy.getY())) {

            deplacerElement(enemy, newX, enemy.getY());// Déplacement de l'ennemi aux nouvelles positions

        } else {

            enemy.attaque(this);
            // Gérer le comportement lorsque l'ennemi atteint les limites de la carte

        }
    }

    public void affiche() {

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                tiles[i][j].affiche();

            }

            System.out.println();

        }

    }

    private boolean estDansLimites(int x, int y) {

        return x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length; // teste si les coordenées sont dans les limites

    }

    // Methods for interacting with tiles, placing towers, moving enemies, etc.
}