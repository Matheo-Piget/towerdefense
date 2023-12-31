package src.main.java.UI.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
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

        Dimension d = new Dimension(1, 200);

        // Créez un JPanel pour le jeu
        JPanel boutton_tour = new JPanel(new GridLayout(1, 6));
        
        JButton tour1 = new JButton("tours 1");
        JButton tour2 = new JButton("tours 2");
        JButton tour3 = new JButton("tours 3");
        JButton tour4 = new JButton("tours 4");
        JButton tour5 = new JButton("tours 5");
        JButton tour6 = new JButton("tours 6");
        boutton_tour.add(tour1);
        boutton_tour.add(tour2);
        boutton_tour.add(tour3);
        boutton_tour.add(tour4);
        boutton_tour.add(tour5);
        boutton_tour.add(tour6);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(d); // Ajuster la taille ici
        bottomPanel.add(boutton_tour, BorderLayout.CENTER);

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
