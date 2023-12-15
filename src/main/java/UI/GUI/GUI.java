package src.main.java.UI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

public class GUI {

    public void startGUIGame(GameMap map, Player player) {

        JFrame frame = new JFrame("Tower Defense");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE); // Couleur de fond du panel principal

        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 10, 10)); // Utilisation d'un layout pour le menu
        menuPanel.setBackground(Color.DARK_GRAY); // Couleur de fond du menu

        // Création de boutons stylés avec des styles personnalisés
        addStyledButton(menuPanel, "Démarrer le jeu", e -> {
            System.out.println("Le jeu démarre !");
            // Action lors du clic sur "Démarrer le jeu"
        });

        addStyledButton(menuPanel, "Reprendre le jeu", e -> {
            System.out.println("Reprise du jeu");
            // Action lors du clic sur "Reprendre le jeu"
        });

        addStyledButton(menuPanel, "Options", e -> {
            System.out.println("Options du jeu");
            // Action lors du clic sur "Options"
        });

        addStyledButton(menuPanel, "Scores", e -> {
            System.out.println("Scores du jeu");
            // Action lors du clic sur "Scores"
        });

        addStyledButton(menuPanel, "Quitter", e -> {
            System.out.println("Au revoir !");
            System.exit(0);
        });

        mainPanel.add(menuPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addStyledButton(JPanel panel, String text, ActionListener listener) {

        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Police et taille
        button.setForeground(Color.WHITE); // Couleur du texte
        button.setBackground(Color.BLUE); // Couleur de fond

        button.setFocusPainted(false); // Supprimer le contour lorsqu'il est sélectionné
        button.setBorderPainted(false); // Supprimer la bordure

        button.setOpaque(true); // Rendre le bouton opaque
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Curseur de la souris au survol du bouton
        button.setPreferredSize(new Dimension(200, 40)); // Taille préférée du bouton

        button.addActionListener(listener);
        
        // Bordure arrondie
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        // Effet de survol
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.GREEN); // Changement de couleur au survol
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.BLUE); // Couleur de fond d'origine
            }
        });

        panel.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.startGUIGame(new GameMap(5, 10), new Player(1000, 3));
        });
    }

}
