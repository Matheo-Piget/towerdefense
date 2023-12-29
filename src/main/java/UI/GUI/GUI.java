package src.main.java.UI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

public class GUI {

    private JFrame frame;
    private JPanel mainPanel;
    private GameMap map;
    private Player player;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Méthode pour démarrer le jeu
    public void startGUIGame(GameMap map, Player player) {
        this.map = map;
        this.player = player;

        // Création de la JFrame principale
        frame = new JFrame("Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1550, 800));
        frame.setLocationRelativeTo(null);

        // Création du panel principal avec une image de fond
        mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("pack/menu/main_menu_usable.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);

        // Création des panels pour le menu et les options
        JPanel menuPanel = createMenuPanel();
        menuPanel.setOpaque(false);
        cardPanel.add(menuPanel, "Menu");

        JPanel optionsPanel = createOptionsPanel();
        cardPanel.add(optionsPanel, "Options");

        // Ajout du panel principal au centre de la JFrame
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        frame.setContentPane(mainPanel);

        // Rendre la JFrame visible
        frame.setVisible(true);
    }

    // Méthode pour créer le panel du menu
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        menuPanel.setOpaque(false);
        menuPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Création de boutons pour le menu principal
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        JPanel empty1 = new JPanel();
        empty1.setOpaque(false);
        JPanel empty2 = new JPanel();
        empty2.setOpaque(false);

        menuPanel.add(empty);
        menuPanel.add(empty1);
        menuPanel.add(empty2);

        addStyledButton(menuPanel, "pack/buttons/start.png", e -> System.out.println("Le jeu démarre !"));
        addStyledButton(menuPanel, "pack/buttons/settings.png", e -> showCard("Options"));
        addStyledButton(menuPanel, "pack/buttons/credits.png", e -> System.out.println("Crédits"));
        addStyledButton(menuPanel, "pack/buttons/quit.png", e -> System.exit(0));

        return menuPanel;
    }

    // Méthode pour créer le panel des options
    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("pack/menu/settings.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        addStyledButton(optionsPanel, "pack/buttons/back.png", e -> showCard("Menu"));

        return optionsPanel;
    }

    // Méthode pour ajouter un bouton stylisé
    private void addStyledButton(JPanel panel, String imagePath, ActionListener listener) {
        try {
            Image img = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(300, 70, Image.SCALE_SMOOTH));

            JButton button = new JButton(icon);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);

            button.addActionListener(listener);

            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBorder(null);
                }
            });

            panel.add(button);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher une carte spécifique (Menu ou Options)
    private void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }

    // Méthode principale pour lancer le jeu
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.startGUIGame(new GameMap(5, 10), new Player(1000, 3));
        });
    }
}
