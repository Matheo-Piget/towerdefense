public class Cellule {

    private Element element;

    public Cellule(){

        element = null;

    }

    public boolean isOccupied() {

        return element != null;
        
    }

    public Element get_elt(){

        return element;

    }

    public void set_elt(Element e){

        this.element = e;

    }

    public void affiche(){

        if(element instanceof Enemy){

            System.out.print("E");

        } else if (element instanceof Tower){

            System.out.print("T");

        } else {

            System.out.print("-");

        }

    }

    // Getters, setters, methods for tower, enemy, etc.
}