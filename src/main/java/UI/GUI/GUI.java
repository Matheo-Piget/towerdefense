package src.main.java.UI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.border.EmptyBorder;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

/**
 * Classe GUI pour gérer l'interface utilisateur.
 */

public class GUI {

    private JFrame frame;
    private JPanel mainPanel;
    private GameMap map;
    private Player player;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private GameState gameState; // Référence à l'état du jeu

    private Clip clip;

    /**
     * Constructeur de GUI
     */
    public GUI(GameMap map, Player player ){

        this.player = player;
        this.map = map;

    }

    public void startGUIGame() {

        frame = new JFrame("ProtectiveTowers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1550, 800));
        frame.setLocationRelativeTo(null);

        // Pour définir l'icone de la fenêtre
        ImageIcon icone = new ImageIcon("src/main/ressources/elements/logo.png");
        frame.setIconImage(icone.getImage());

        // Pour lancer la musique du main_menu
        playSpecificMusic("src/main/ressources/music/mainmenumusic.wav");
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        mainPanel = createMainPanel();

        cardLayout = new CardLayout(); // on créer un cardlayout pour changer de fentre plus facilement
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);

        JPanel menuPanel = createMenuPanel();
        menuPanel.setOpaque(false);
        cardPanel.add(menuPanel, "Menu");

        JPanel optionsPanel = createOptionsPanel();
        menuPanel.setOpaque(false);
        cardPanel.add(optionsPanel, "Options");

        JPanel creditsPanel = createCreditsPanel();
        menuPanel.setOpaque(false);
        cardPanel.add(creditsPanel, "Crédits");

        mainPanel.add(cardPanel, BorderLayout.CENTER);
        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }

    /**
     * @return un Jpanel pour le menu
     */
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        menuPanel.setOpaque(false);
        menuPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // laisse des Jpanel pour plus d'esthétisme
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        JPanel empty1 = new JPanel();
        empty1.setOpaque(false);
        JPanel empty2 = new JPanel();
        empty2.setOpaque(false);

        menuPanel.add(empty);
        menuPanel.add(empty1);
        menuPanel.add(empty2);

        addStyledButton(menuPanel, "src/main/ressources/buttons/gamebuttons/start.png",
                e -> displayRulesPanel());
        addStyledButton(menuPanel, "src/main/ressources/buttons/gamebuttons/settings.png",
                e -> showCard("Options"));
        addStyledButton(menuPanel, "src/main/ressources/buttons/gamebuttons/credits.png",
                e -> showCard("Crédits"));
        addStyledButton(menuPanel, "src/main/ressources/buttons/gamebuttons/quit.png",
                e -> System.exit(0));

        return menuPanel;
    }

    public JPanel createMainPanel() {

        return new JPanel(new BorderLayout()) { // on créer un panel general pour mettre une image en fond pour le
                                                // menu
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("src/main/ressources/menu/main_menu_usable.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

    }

    /**
     * @return un Jpanel pour les crédits
     */
    private JPanel createCreditsPanel() {
        JPanel creditsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("src/main/ressources/menu/credits.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        creditsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        addStyledButton(creditsPanel, "src/main/ressources/buttons/gamebuttons/back.png", e -> showCard("Menu"));

        return creditsPanel;

    }

    private JPanel createRulesPanel() {
        JPanel rulesPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("src/main/ressources/menu/rules.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        JPanel empty = new JPanel();
        empty.setOpaque(false);

        rulesPanel.add(empty, BorderLayout.NORTH);
        rulesPanel.add(empty, BorderLayout.SOUTH);
        rulesPanel.add(empty, BorderLayout.EAST);
        rulesPanel.add(empty, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1));
        centerPanel.setOpaque(false);

        for (int i = 0; i < 4; i++) {
            empty = new JPanel();
            empty.setOpaque(false);
            centerPanel.add(empty);
        }

        rulesPanel.add(centerPanel, BorderLayout.CENTER);

        try {
            Image img = ImageIO.read(new File("src/main/ressources/buttons/gamebuttons/logo.png"));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(120, 120, Image.SCALE_SMOOTH));

            JButton startButton = new JButton(icon);
            startButton.setBorderPainted(false);
            startButton.setFocusPainted(false);
            startButton.setContentAreaFilled(false);

            startButton.addActionListener(e -> startGame());
            centerPanel.add(startButton);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rulesPanel;
    }

    private void displayRulesPanel() {
        JPanel rulesPanel = createRulesPanel();
        cardPanel.add(rulesPanel, "Rules");
        showCard("Rules");
    }

    /**
     * @return un Jpanel pour les options
     */
    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("src/main/ressources/menu/settings.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };


        optionsPanel.setLayout(new GridLayout(7, 2));
        addStyledButton(optionsPanel, "src/main/ressources/buttons/gamebuttons/back.png", e -> showCard("Menu"));

        JPanel empty = new JPanel();
        empty.setOpaque(false);
        JPanel empty1 = new JPanel();
        empty1.setOpaque(false);
        JPanel empty2 = new JPanel();
        empty2.setOpaque(false);

        JPanel empty3 = new JPanel();
        empty3.setOpaque(false);
        JPanel empty4 = new JPanel();
        empty4.setOpaque(false);
        JPanel empty5 = new JPanel();
        empty5.setOpaque(false);

        optionsPanel.add(empty);

        JPanel difficulty = new JPanel(new GridLayout(1, 6));
        difficulty.add(empty3);
        difficulty.add(empty4);
        difficulty.setOpaque(false);

        
        addStyledButton(optionsPanel, "src/main/ressources/buttons/gamebuttons/mute.png", e -> stopSpecificMusic());

        addStyledButton(difficulty, "src/main/ressources/buttons/gamebuttons/easy.png", e -> {GameMap.difficulty = 1; player.ajustLifeAccordingDifficulty();});
        addStyledButton(difficulty, "src/main/ressources/buttons/gamebuttons/medium.png", e -> {GameMap.difficulty = 2; player.ajustLifeAccordingDifficulty();});
        addStyledButton(difficulty, "src/main/ressources/buttons/gamebuttons/hard.png", e -> {GameMap.difficulty = 3; player.ajustLifeAccordingDifficulty();});

        optionsPanel.add(difficulty);

        return optionsPanel;
    }

    /**
     * méthode qui ajoute un boutton au panel mis en parametre, avec une image et un
     * listener mis en parametre
     */
    private void addStyledButton(JPanel panel, String imagePath, ActionListener listener) {
        try {
            ImageIcon icon;
            JPanel temp = new JPanel(new FlowLayout());
            temp.setOpaque(false);
            Image img = ImageIO.read(new File(imagePath));
            if(imagePath == "src/main/ressources/buttons/gamebuttons/mute.png" || imagePath == "src/main/ressources/buttons/gamebuttons/easy.png" || imagePath == "src/main/ressources/buttons/gamebuttons/medium.png" ||imagePath == "src/main/ressources/buttons/gamebuttons/hard.png" ){icon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));}
            else {icon = new ImageIcon(img.getScaledInstance(300, 70, Image.SCALE_SMOOTH));}

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

            temp.add(button);
            panel.add(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * methode pour switch de card via le nom mis en parametre
     */
    private void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }

    /** Jouer un fichier audio (pour les musiques) */
    private void playSpecificMusic(String cheminMusique) {
        try {
            File fichierAudio = new File(cheminMusique);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fichierAudio);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);

            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /** Pour arrêter la musique */
    private void stopSpecificMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * lance le jeu
     */
    public void startGame() {

        // Création de l'état du jeu
        gameState = new GameState(map, player);
        cardPanel.add(gameState.getGamePanel(), "Game");
        showCard("Game");
        cardPanel.setVisible(true);
        cardPanel.setOpaque(false);

        stopSpecificMusic();
        playSpecificMusic("src/main/ressources/music/ingamemusic.wav");
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        gameState.startGameLoop();

    }
}
