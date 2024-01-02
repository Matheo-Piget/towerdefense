package src.main.java.UI.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import src.main.java.configMap.GameMap;
import src.main.java.model.Tower;
import src.main.java.start.Player;

/**
 * Gère l'état du jeu et la logique de la fenêtre de jeu.
 */
public class GameState {
    private GameMap gameMap;
    private JPanel gamePanel;
    private GameMapPanel gameMapPanel;
    private String selectedTowerType;

    /**
     * Constructeur prenant la carte du jeu et le joueur.
     * Initialise la carte du jeu avec des ennemis et le panneau de jeu avec des boutons pour placer des tours.
     * @param map La carte du jeu.
     * @param player Le joueur.
     */
    public GameState(GameMap map, Player player) {
        gameMap = map;
        gameMap.nouveauxEnemy(); // Ajoute des ennemis à la carte
        gameMap.nouveauxEnemy(); // Ajoute plus d'ennemis à la carte
        gameMapPanel = new GameMapPanel(map); // Initialise le panneau de la carte du jeu

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); // Utilisation de BoxLayout horizontal

        // Ajout d'un espace entre les boutons pour les élargir visuellement
        topPanel.add(Box.createHorizontalStrut(10)); // Espace initial

        // Crée des boutons pour chaque type de tour et les ajoute au panneau supérieur
        for (int i = 1; i <= 4; i++) {
            String towerType = "Tower " + i;
            JButton towerButton = createTowerButton(towerType);
            topPanel.add(towerButton);
            topPanel.add(Box.createHorizontalStrut(20)); // Espace entre les boutons
        }

        // Ajout d'un espace final pour la mise en page
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.setPreferredSize(new Dimension(1, 200));

        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameMapPanel, BorderLayout.CENTER); // Ajoute le panneau de la carte au centre
        gamePanel.add(topPanel, BorderLayout.NORTH); // Ajoute le panneau supérieur en haut
    }

    /**
     * Crée un bouton pour un type de tour spécifique.
     * @param towerType Le type de tour associé au bouton.
     * @return Le bouton créé.
     */
    private JButton createTowerButton(String towerType) {
        JButton button = new JButton(towerType);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedTowerType = towerType;
                gameMapPanel.setTowerToPlace(towerType);
            }
        });
        return button;
    }

    /**
     * Démarre la boucle de jeu en utilisant un Timer.
     */
    public void startGameLoop() {
        Timer timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameMapPanel.repaint(); // Rafraîchit l'affichage de la carte du jeu
                //updateGame(); // Appel à la méthode de mise à jour du jeu
            }
        });
        timer.start(); // Démarre le Timer pour la boucle de jeu
    }

    /**
     * Récupère le panneau de jeu.
     * @return Le panneau de jeu.
     */
    public JPanel getGamePanel() {
        return gamePanel; // Renvoie le panneau de jeu
    }

    /**
     * Met à jour le jeu en déplaçant les ennemis et en ajoutant de nouveaux ennemis.
     */
    public void updateGame() {
        gameMap.deplacerTousLesEnnemis(); // Déplace tous les ennemis sur la carte
        gameMap.nouveauxEnemy(); // Ajoute de nouveaux ennemis
    }

    /**
     * Renvoie le type de tour sélectionné.
     * @return Le type de tour sélectionné.
     */
    public String getSelectedTowerType() {
        return selectedTowerType; // Renvoie le type de tour sélectionné
    }
}
