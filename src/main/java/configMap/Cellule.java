package src.main.java.configMap;

import src.main.java.model.*;

/**
 * La classe Cellule représente une cellule de la carte du jeu.
 * On pourra consulter la présence d'un élément sur la cellule, et le cas
 * échéant, lequel.
 */

public class Cellule {

    private Element element;

    public Cellule() {
        element = null;
    }

    public Cellule(Element e) {
        element = e;
    }

    /* Pour savoir si la cellule contient quelque chose */
    public boolean isOccupied() {
        return element != null;
    }

    /* Pour récupérer dans la classe GameMap l'élement en question */
    public Element getElement() {
        return element;
    }

    /* Pour modifier dans la classe GameMap l'élement pris en argument */
    public void setElement(Element e) {
        this.element = e;
    }

    /* Pour afficher l'élement sur le type de l'élement sur le tableau de jeu */
    public void affiche() {
        if (element instanceof Enemy) {
            System.out.print("E");
        } else if (element instanceof Tower) {
            System.out.print("T");
        } else {
            System.out.print("-");

        }

    }

    // Getters, setters, methods for tower, enemy, etc.
}