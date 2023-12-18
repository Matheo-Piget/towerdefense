package src.main.java.start;

/**
 * Classe représentant le joueur
 * Cette classe est uniquement présente pour modéliser le joueur et ses
 * capacités d'action sur le jeu
 */
public class Player {

    /* Argent et vies */
    private int money;
    private int lives;

    /**
     * Constructeur de la classe Player
     * 
     * @param money Argent du joueur
     * @param lives Vies du joueur
     */
    public Player(int money, int lives) {
        this.money = money;
        this.lives = lives;
    }

    /** Méthode permettant de mettre à jour le joueur */
    public void update() {
        // TODO mettre a jour le joueur
    }

    /** Affichage des caractéristiques du joueur */
    public void affiche() {
        System.out.println("Vous avez : " + money + " d'argent et " + lives + " vies");
    }

    /* Getter et Setter de Lives */
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    /* Getter et Setter de Money */
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}