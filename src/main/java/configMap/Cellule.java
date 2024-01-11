package src.main.java.configMap;

import src.main.java.model.*;
import src.main.java.model.enemies.*;
import src.main.java.model.towers.*;

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
    public Element getElem() {
        return element;
    }

    /** Pour modifier dans la classe GameMap l'élement pris en argument */
    public void setElem(Element e) {
        this.element = e;
    }

    /**
     * Pour afficher l'élement sur le type de l'élement sur le tableau de jeu
     * "\u001B[0m" sert à reset la couleur
     * "\u001B[32m" désigne la couleur verte
     * "\u001B[33m" désigne la couleur jaune
     * "\u001B[34m" désigne la couleur bleue
     * "\u001B[31m" désigne la couleur rouge
     */
    public void affiche() {
        if (element instanceof Kryon) {
            System.out.print("\u001B[32m" + "Kr" + "\u001B[0m");
        } else if (element instanceof Dreth) {
            System.out.print("\u001B[33m" + "Dr" + "\u001B[0m");
        } else if (element instanceof Fyron) {
            System.out.print("\u001B[34m" + "Fy" + "\u001B[0m");
        } else if (element instanceof Gazer) {
            System.out.print("\u001B[31m" + "Ga" + "\u001B[0m");
        } else if (element instanceof Liche) {
            System.out.print("\u001B[31m" + "Li" + "\u001B[0m");
        } else if (element instanceof Zorch) {
            System.out.print("\u001B[31m" + "Zo" + "\u001B[0m");
        } else if (element instanceof FightTower) {
            System.out.print("\u001B[32m" + "f♖" + "\u001B[0m");
        } else if (element instanceof SniperTower) {
            System.out.print("\u001B[33m" + "s♖" + "\u001B[0m");
        } else if (element instanceof TntTower) {
            System.out.print("\u001B[31m" + "t♖" + "\u001B[0m");
        } else if (element instanceof BulletTower) {
            System.out.print("\u001B[34m" + "b♖" + "\u001B[0m");
        } else if (element instanceof NukeTower) {
            System.out.print("\u001B[34m" + "n♖" + "\u001B[0m");
        } else if (element instanceof SpeedTower) {
            System.out.print("\u001B[34m" + "s♖" + "\u001B[0m");
        } else {
            System.out.print("--");
        }
    }

}