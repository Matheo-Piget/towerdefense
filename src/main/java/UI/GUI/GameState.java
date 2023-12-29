package src.main.java.UI.GUI;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

public class GameState {
    private GameMap gameMap;
    private Player player;
    private GameMapPanel gameMapPanel;

    public GameState(GameMap map, Player player) {
        this.gameMap = map;
        this.player = player;
        this.gameMapPanel = new GameMapPanel(map);
    }

    public void startGame() {
        // Initialiser le jeu
        gameMap.nouveauxEnemy(); // À définir selon ta logique

        // Initialiser l'affichage
        gameMapPanel.setVisible(true);

        // Rafraîchir l'affichage
        gameMapPanel.repaint();
    }

    // Autres méthodes pour la logique de jeu
}
