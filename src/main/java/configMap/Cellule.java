package src.main.java.configMap;

import src.main.java.model.*;

/**
 * La classe Cellule représente une cellule de la carte du jeu.
 * On pourra consulter la présence d'un élément sur la cellule, et le cas
 * échéant, lequel.
 * 
 * @see GameMap pour la carte du jeu
 * @see Element pour les éléments du jeu
 */

public class Cellule {

    private Element element;

    public Cellule() {
        element = null;
    }

    public Cellule(Element e) {
        element = e;
    }

    /** @return true si la cellule contient quelque chose, false sinon */
    public boolean isOccupied() {
        return element != null;
    }

    /** @return l'élément présent sur la cellule */
    public Element getElement() {
        return element;
    }

    /** Pour modifier dans la classe GameMap l'élement pris en argument */
    public void setElement(Element element) {
        this.element = element;
    }

    /** Pour afficher l'élement sur le type de l'élement sur le tableau de jeu */
    public void affiche() {
        if (element instanceof Enemy) {
            System.out.print("E");
        } else if (element instanceof Tower) {
            System.out.print("T");
        } else {
            System.out.print("-");

        }

    }
}