package src.main.java.UI.GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.main.java.configMap.GameMap;
import src.main.java.model.Enemy;
import src.main.java.model.Tower;

public class GameMapPanel extends JPanel {
    private GameMap gameMap;
    private Image towerImage;
    private Image enemyImage;

    public GameMapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        gameMap.nouveauxEnemy();
        try {
            // Charger les images des tours et des ennemis
            towerImage = ImageIO.read(new File("pack/towers/tempo.png"));
            enemyImage = ImageIO.read(new File("pack/enemies/enemy1/1_enemies_1_run_000.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner les tours
        for (Tower tower : gameMap.tout_les_tower()) {
            int x = tower.getX();
            int y = tower.getY();

            // Dessiner l'image de la tour à sa position (x, y)
            g.drawImage(towerImage, x, y, this);
        }

        // Dessiner les ennemis
        for (Enemy enemy : gameMap.tout_les_enemy()) {
            int x = enemy.getX();
            int y = enemy.getY();

            // Dessiner l'image de l'ennemi à sa position (x, y)
            g.drawImage(enemyImage, x, y, this);
        }
    }
}
