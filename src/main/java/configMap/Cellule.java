package src.main.java.configMap;

import src.main.java.model.*;

public class Cellule {

    private Element element;

    public Cellule() {

        element = null;

    }

    public Cellule(Element e) {

        element = e;

    }

    public boolean isOccupied() {

        return element != null;

    }

    public Element get_elt() {

        return element;

    }

    public void set_elt(Element e) {

        this.element = e;

    }

    public void affiche() {

        if (element instanceof WeakEnemy) {

            System.out.print("wE");

        } else if (element instanceof MediumEnemy) {

            System.out.print("mE");

        } else if (element instanceof RangeEnemy) {

            System.out.print("rE");

        }else if (element instanceof StrongEnemy) {

            System.out.print("sE");

        }else if (element instanceof WeakTower) {

            System.out.print("wT");

        }else if (element instanceof MediumTower) {

            System.out.print("mT");

        }else if (element instanceof StrongTower) {

            System.out.print("sT");

        }else if (element instanceof FastTower) {

            System.out.print("fT");

        }else {

            System.out.print("--");

        }

    }

    // Getters, setters, methods for tower, enemy, etc.
}