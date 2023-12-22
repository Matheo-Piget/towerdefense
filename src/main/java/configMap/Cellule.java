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
    public Element get_elt() {
        return element;
    }

    /** Pour modifier dans la classe GameMap l'élement pris en argument */
    public void set_elt(Element e) {
        this.element = e;
    }

    /** Pour afficher l'élement sur le type de l'élement sur le tableau de jeu */
    public void affiche() {

        if (element instanceof WeakEnemy) {

            System.out.print("wE");

        } else if (element instanceof MediumEnemy) {

            System.out.print("mE");

        } else if (element instanceof RangeEnemy) {

            System.out.print("rE");

        } else if (element instanceof StrongEnemy) {

            System.out.print("sE");

        } else if (element instanceof WeakTower) {

            System.out.print("wT");

        } else if (element instanceof MediumTower) {

            System.out.print("mT");

        } else if (element instanceof StrongTower) {

            System.out.print("sT");

        } else if (element instanceof FastTower) {

            System.out.print("fT");

        } else {

            System.out.print("--");

        }

    }

    // Getters, setters, methods for tower, enemy, etc.
}