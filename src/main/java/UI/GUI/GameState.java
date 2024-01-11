package src.main.java.UI.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.*;
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
    private JLabel scoreLabel;
    private JPanel gameOverPanel;

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
        gameMapPanel = new GameMapPanel(map, this.player); // Initialise le panneau de la carte du jeu

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
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

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
        scoreLabel = new JLabel("Score: " + player.score);

        // Application de la police aux labels
        Font newCustomFont = new Font("Daydream", Font.BOLD, 16); // Exemple avec la police Arial, en gras, taille 16
        Color customColor = Color.decode("#ffffff"); // Exemple de couleur blanche
        livesLabel.setForeground(customColor); // Application de la couleur à l'étiquette lives
        moneyLabel.setForeground(customColor); // Application de la couleur à l'étiquette money
        scoreLabel.setForeground(customColor);
        livesLabel.setFont(newCustomFont);
        moneyLabel.setFont(newCustomFont);
        scoreLabel.setFont(newCustomFont);

        // On pousse un peu le label de score vers la droite
        scoreLabel.setLocation(900, 100);

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
        topPanel.add(scoreLabel);
        topPanel.add(iconLabel_money);
        topPanel.add(moneyLabel);
        topPanel.add(scoreLabel);

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

        button.setBackground(Color.WHITE); // Couleur de fond
        button.setOpaque(true); // Rend le fond visible

        // Ajouter un effet de survol
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY); // Change la couleur de fond lorsqu'on survole le bouton
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE); // Rétablit la couleur de fond initiale quand on quitte le bouton
            }
        });

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
        button.setBorder(
                BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(10, 15, 10, 15)));

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
                    player.setMoney(gameMap.update(player) + player.getMoney());
                    enemySpawnCounter = 0;
                }

                if (player.gameOver()) {
                    // Arrêter le timer si le joueur n'a plus de vie
                    ((Timer) e.getSource()).stop();
                    // Autres actions à effectuer lorsque le jeu s'arrête, par exemple afficher un
                    // message de fin
                    showGameOver();
                }

                // Mettre à jour les valeurs des étiquettes
                updatePlayerInfoLabels();
            }
        });
        timer.start();
    }

    // Méthode pour afficher l'écran de Game Over
    private void showGameOver() {
        // Création d'un nouveau panneau pour l'écran Game Over
        gameOverPanel = new JPanel();
        gameOverPanel.setLayout(new BorderLayout());

        // Chargement de l'image de Game Over dans un JLabel
        ImageIcon gameOverImage = new ImageIcon("src/main/ressources/menu/main_menu_usable.jpg");
        JLabel gameOverLabel = new JLabel(gameOverImage);

        // Ajout du bouton de rejouer à ce panneau
        JButton restartButton = new JButton(new ImageIcon("src/main/ressources/buttons/gamebuttons/retry.png"));
        restartButton.setOpaque(false);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.reset();
                gameMap.reset();
                for (Component comp : gamePanel.getComponents()) {

                    comp.setVisible(true);

                }
                gameOverPanel.setVisible(false); // Cache le panel Game Over
                restartButton.setVisible(false); // Cache le bouton de redémarrage
                restartButton.setOpaque(false);

                gamePanel.repaint(); // Redessine le panneau de jeu
                startGameLoop();

            }
        });

        for (Component comp : gamePanel.getComponents()) {

            comp.setVisible(false);

        }

        // Ajout du label et du bouton au panneau
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(restartButton, BorderLayout.SOUTH);

        // Affichage du panneau Game Over dans la fenêtre de jeu
        gamePanel.add(gameOverPanel, BorderLayout.CENTER);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    // Méthode pour mettre à jour les valeurs des étiquettes de vies et d'argent
    private void updatePlayerInfoLabels() {
        // Récupérer les nouvelles valeurs du joueur
        int lives = player.getLives();
        int money = player.getMoney();

        // Mettre à jour les étiquettes avec les nouvelles valeurs

        livesLabel.setText("Lives: " + lives);
        moneyLabel.setText("Money: " + money);
        scoreLabel.setText("Score: " + player.score);

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
