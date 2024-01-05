package src.main.java.UI.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;


import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

/**
 * Cette classe gère l'état du jeu et la logique de la fenêtre de jeu.
 */
public class GameState {
    private GameMap gameMap;
    private JPanel gamePanel;
    private GameMapPanel gameMapPanel;
    private String selectedTowerType;
    private Player player;
    private Image fond;
    int enemySpawnInterval = 15;
    int enemySpawnCounter = 0;
    private JLabel livesLabel;
    private JLabel moneyLabel;

    /**
     * Constructeur prenant la carte du jeu et le joueur.
     * Initialise la carte du jeu avec des ennemis et le panneau de jeu avec des
     * boutons pour placer des tours.
     * 
     * @param map    La carte du jeu.
     * @param player Le joueur.
     */
    public GameState(GameMap map, Player player) {
        gameMap = map;
        this.player = player;
        gameMapPanel = new GameMapPanel(map); // Initialise le panneau de la carte du jeu

        // Chargement de l'image de fond
        try {
            fond = ImageIO.read(new File("src/main/ressources/menu/ingame.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Création du panneau de boutons pour les tours
        JPanel topPanel = createTopPanel();
        createGamePanel(topPanel);
    }

    // Création du panneau de boutons pour les tours
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fond != null) {
                    g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
                } else {
                    System.out.println("debug ");
                }
            }
        };
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(Box.createHorizontalStrut(10));

        // Chemins des images des boutons de tour
        String[] towerImages = {
                "src/main/ressources/buttons/towerbuttons/bullet.jpg",
                "src/main/ressources/buttons/towerbuttons/fight.jpg",
                "src/main/ressources/buttons/towerbuttons/nuke.jpg",
                "src/main/ressources/buttons/towerbuttons/sniper.jpg",
                "src/main/ressources/buttons/towerbuttons/speed.jpg",
                "src/main/ressources/buttons/towerbuttons/tnt.jpg"
        };

        String[] money_livesImages = {

            "src/main/ressources/elements/dollar.png",
            "src/main/ressources/elements/heart.png"


        };

        // Création des boutons pour chaque type de tour
        String t1 = "Fight Tower";
        JButton towerButton1 = createTowerButtonWithImage(t1, towerImages[1], 50, 50);
        topPanel.add(towerButton1);
        String t2 = "Bullet Tower";
        JButton towerButton2 = createTowerButtonWithImage(t2, towerImages[0], 50, 50);
        topPanel.add(towerButton2);
        String t3 = "Nuke Tower";
        JButton towerButton3 = createTowerButtonWithImage(t3, towerImages[2], 50, 50);
        topPanel.add(towerButton3);
        String t4 = "Sniper Tower";
        JButton towerButton4 = createTowerButtonWithImage(t4, towerImages[3], 50, 50);
        topPanel.add(towerButton4);
        String t5 = "Speed Tower";
        JButton towerButton5 = createTowerButtonWithImage(t5, towerImages[4], 50, 50);
        topPanel.add(towerButton5);
        String t6 = "TnT Tower";
        JButton towerButton6 = createTowerButtonWithImage(t6, towerImages[5], 50, 50);
        topPanel.add(towerButton6);

        // Création des labels pour les informations du joueur (vie et argent)
        livesLabel = new JLabel("Lives: " + player.getLives());
        moneyLabel = new JLabel("Money: " + player.getMoney());

        // Chargement de l'icône depuis le fichier
        ImageIcon livesicon = new ImageIcon(money_livesImages[1]);

        // Redimensionnement de l'icône à une taille spécifique
        Image scaledImage_lives = livesicon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // Création d'un ImageIcon redimensionné
        ImageIcon scaledIcon_lives = new ImageIcon(scaledImage_lives);

        // Création d'un JLabel pour afficher l'icône redimensionnée
        JLabel iconLabel_lives = new JLabel(scaledIcon_lives);

        // Chargement de l'icône depuis le fichier
        ImageIcon moneyIcon = new ImageIcon(money_livesImages[0]);

        // Redimensionnement de l'icône à une taille spécifique
        Image scaledImage_money = moneyIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // Création d'un ImageIcon redimensionné
        ImageIcon scaledIcon_money = new ImageIcon(scaledImage_money);

        // Création d'un JLabel pour afficher l'icône redimensionnée
        JLabel iconLabel_money = new JLabel(scaledIcon_money);

        // Ajout des labels à côté des boutons dans le panneau supérieur
        topPanel.add(iconLabel_lives);
        topPanel.add(livesLabel);
        topPanel.add(iconLabel_money);
        topPanel.add(moneyLabel);

        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.setPreferredSize(new Dimension(1, 200));
        topPanel.repaint();
        return topPanel;
    }

    // Création du panneau de jeu avec la carte et les boutons pour les tours
    private void createGamePanel(JPanel topPanel) {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameMapPanel, BorderLayout.CENTER);
        gamePanel.add(topPanel, BorderLayout.NORTH);
    }

    // Création d'un bouton pour un type de tour spécifique avec une image
    // redimensionnée
    private JButton createTowerButtonWithImage(String towerType, String imagePath, int width, int height) {
        JButton button = new JButton(towerType);

        try {
            Image originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            button.setIcon(resizedIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ajout d'une bordure arrondie aux boutons
        Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        button.setBorder(BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(10, 15, 10, 15)));

        // Action lorsqu'un bouton de tour est cliqué
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedTowerType = towerType;
                gameMapPanel.setTowerToPlace(towerType);
            }
        });
        return button;
    }

    // Démarre la boucle de jeu avec un Timer
    public void startGameLoop() {
        Timer timer = new Timer(200, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameMapPanel.repaint();
                enemySpawnCounter++;
                if (enemySpawnCounter >= enemySpawnInterval) {
                    player.setMoney(gameMap.update(player)+ player.getMoney());
                    enemySpawnCounter = 0;
                }

                 // Mettre à jour les valeurs des étiquettes
                updatePlayerInfoLabels();
            }
        });
        timer.start();
    }

    // Méthode pour mettre à jour les valeurs des étiquettes de vies et d'argent
private void updatePlayerInfoLabels() {
    // Récupérer les nouvelles valeurs du joueur
    int lives = player.getLives();
    int money = player.getMoney();

    // Mettre à jour les étiquettes avec les nouvelles valeurs
    livesLabel.setText("Lives: " + lives);
    moneyLabel.setText("Money: " + money);

    // Actualiser l'affichage
    gamePanel.revalidate();
    gamePanel.repaint();
}

    // Renvoie le panneau de jeu
    public JPanel getGamePanel() {
        return gamePanel;
    }

    // Met à jour le jeu en déplaçant les ennemis et en ajoutant de nouveaux ennemis
    public void updateGame() {
        gameMap.update(player);
    }

    // Renvoie le type de tour sélectionné
    public String getSelectedTowerType() {
        return selectedTowerType;
    }

}
