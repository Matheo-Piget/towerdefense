public class Player {

    private int money;

    public Player(int money) {

        this.money = money;
        
    }

    public void affiche(){

        System.out.println("Vous avez : "+ money);

    }

    // Getters, setters, and methods for managing money, lives, etc.
}