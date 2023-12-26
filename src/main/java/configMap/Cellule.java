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

    /** Symbolise l'élément présent dans la cellule du jeu */
    private Element element;

    /** Constructeurs des deux cas */
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
            System.out.print("\u001B[32m" + "wE" + "\u001B[0m");
        } else if (element instanceof MediumEnemy) {
            System.out.print("\u001B[33m" + "mE" + "\u001B[0m");
        } else if (element instanceof RangeEnemy) {
            System.out.print("\u001B[34m" + "rE" + "\u001B[0m");
        } else if (element instanceof StrongEnemy) {
            System.out.print("\u001B[31m" + "sE" + "\u001B[0m");
        } else if (element instanceof WeakTower) {
            System.out.print("\u001B[32m" + "w♖" + "\u001B[0m");
        } else if (element instanceof MediumTower) {
            System.out.print("\u001B[33m" + "m♖" + "\u001B[0m");
        } else if (element instanceof StrongTower) {
            System.out.print("\u001B[31m" + "s♖" + "\u001B[0m");
        } else if (element instanceof FastTower) {
            System.out.print("\u001B[34m" + "f♖" + "\u001B[0m");
        } else {
            System.out.print("--");
        }
    }

}