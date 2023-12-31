package src.main.java.UI.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
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

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(d); // Ajuster la taille ici
        bottomPanel.add(buttonTour(), BorderLayout.CENTER);

        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameMapPanel, BorderLayout.CENTER);
        gamePanel.add(bottomPanel, BorderLayout.NORTH);
    }

    private JPanel buttonTour() {

        JPanel boutton_tour = new JPanel(new GridLayout(1, 6));

        for(int i = 0; i < 6; i++){

            boutton_tour.add(new JButton(("Tour " + (i+1))));

        }

        return boutton_tour;

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
