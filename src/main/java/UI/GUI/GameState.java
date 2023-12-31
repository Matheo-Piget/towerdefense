package src.main.java.UI.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

public class GameState {
    private GameMap gameMap;
    private JPanel gamePanel;
    private Player player;
    private GameMapPanel gameMapPanel;

    GameState(GameMap map, Player player) {
        gameMap = map;
        gameMap.nouveauxEnemy();
        gameMap.nouveauxEnemy();
        this.player = player;
        gameMapPanel = new GameMapPanel(map);

        // Créez un JPanel pour le jeu
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1, 200)); // Ajuster la taille ici
        
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameMapPanel, BorderLayout.CENTER);
        gamePanel.add(bottomPanel, BorderLayout.NORTH);
    }

    public void startGameLoop() {
        Timer timer = new Timer(1500, e -> {
            // Mettre à jour le jeu
            updateGame();
            
    
            // Rafraîchir l'affichage
            gameMapPanel.repaint();
        });
        timer.start();
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void updateGame(){

        gameMap.deplacerTousLesEnnemis();
        gameMap.nouveauxEnemy();


    }

    // Autres méthodes pour la logique de jeu
}
