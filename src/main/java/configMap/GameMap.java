package src.main.java.configMap;

import src.main.java.model.*;
import src.main.java.start.*;

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

    public boolean placer(Element e) {

        int x = e.getX();
        int y = e.getY();

        if (estDansLimites(x, y) && !tiles[x][y].isOccupied()) { // on regarde si les coordnonnées sont dans les limites et que la cellule en question est vide

            tiles[x][y].set_elt(e);
            return true; 

        }
        
        return false;

    }

    public void retirerElement(Element element) {

        int x = element.getX();
        int y = element.getY();

        if (estDansLimites(x, y)) {
            tiles[x][y].set_elt(null);
        } else {
            System.out.println("Coordonnées hors limites !");
        }
    }

    public Tower trouverTowerSurMemeLigne(Enemy e){

        int enemyY = e.getY();

        for (int i = tiles[enemyY].length; i > 0; i--) {

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
            if (tiles[newX][newY] == null) {

                tiles[oldX][oldY] = null; // Supprime l'élément de son ancienne position
                element.setX(newX);
                element.setY(newY);
                tiles[newX][newY].set_elt(element);; // Place l'élément à sa nouvelle position
                return true;

            } else {

                return false;

            }

        } else {

            return false;

        }
    }

    public void enemyMort(){ // supprime tout les enemies qui n'ont plus de vie

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                if(tiles[i][j].get_elt().getHealth() <= 0 && tiles[i][j].get_elt() instanceof Enemy) retirerElement(tiles[i][j].get_elt()); // on teste si l'élément n'a plus de vie et si c'est un enemy
                
            }
        }

    }

    public void towerMorte(){ // supprime tout les tours qui n'ont plus de vie

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                if(tiles[i][j].get_elt().getHealth() <= 0 && tiles[i][j].get_elt() instanceof Enemy) retirerElement(tiles[i][j].get_elt()); // on teste si l'élément n'a plus de vie et si c'est une tour
                
            }
        }

    }

    public void update(){

        enemyMort(); // on supprime tout les enemis mort
        towerMorte(); // meme chose pour les tours
        //nouveauxEnemy(); TODO -> faire apparaître de nouveaux enemies suivant un timer ou autre alternative

        deplacerTousLesEnnemis(); // on déplace tout les enemis

    }

    // Méthode pour déplacer tous les ennemis en fonction de leur vitesse
    public void deplacerTousLesEnnemis() {

        for (Cellule[] cellules : tiles) {

            for (Cellule cellule : cellules) {


                if(cellule.get_elt() instanceof Enemy) deplacerEnnemi((Enemy) cellule.get_elt()); // Appel de la méthode pour déplacer un ennemi
        
                
            }
        }

    }

    // Méthode pour déplacer un ennemi spécifique
    private void deplacerEnnemi(Enemy enemy) {

        // Logique pour calculer les déplacements en fonction de la vitesse de l'ennemi
        int deplacementX = enemy.getX() - enemy.getSpeed();

        // Calcul des nouvelles positions
        int newX = enemy.getX() + deplacementX;

        // Vérification si les nouvelles positions sont à l'intérieur des limites de la carte
        if (estDansLimites(newX, enemy.getY())) {

            deplacerElement(enemy, newX, enemy.getY());// Déplacement de l'ennemi aux nouvelles positions

        } else {

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

        return x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length; // teste si les coordenées sont dans les limites

    }

    // Methods for interacting with tiles, placing towers, moving enemies, etc.
}