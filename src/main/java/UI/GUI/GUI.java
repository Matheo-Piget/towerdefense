package src.main.java.UI.GUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 10, 10)); // Utilisation d'un layout pour le menu

        // Ajout des boutons avec des styles personnalisés
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
        button.addActionListener(listener);
        panel.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.startGUIGame(new GameMap(5, 10), new Player(1000, 3));
        });
    }

}
