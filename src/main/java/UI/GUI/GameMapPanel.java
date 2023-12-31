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
    private Image backgroundImage; // Image de fond des cellules
    private int cellWidth; // Largeur des cellules
    private int cellHeight;

    public GameMapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        
        try {
            // Charger les images des tours et des ennemis
            towerImage = ImageIO.read(new File("pack/towers/tempo.png"));
            enemyImage = ImageIO.read(new File("pack/enemies/enemy1/1_enemies_1_run_000.png"));
            backgroundImage = ImageIO.read(new File("pack/buttons/pause.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        cellWidth = getWidth() / gameMap.getCols(); // À adapter selon la structure de votre GameMap
        cellHeight = getHeight() / gameMap.getRows();

        for (int i = 0; i < gameMap.getCols(); i++) {
            for (int j = 0; j < gameMap.getRows(); j++) {
                int x = i * cellWidth;
                int y = j * cellHeight;

                // Dessinez l'image de fond de la cellule
                g.drawImage(backgroundImage, x, y, cellWidth, cellHeight, this);

                // Dessinez l'élément sur la cellule si nécessaire
                // ...
            }
        }

        // Dessinez les tours
        for (Tower tower : gameMap.tout_les_tower()) {
            int towerX = tower.getX() * cellWidth;
            int towerY = tower.getY() * cellHeight;

            // Dessinez l'image de la tour à sa position (x, y)
            g.drawImage(towerImage, towerX, towerY, cellWidth, cellHeight, this);
        }

        // Dessinez les ennemis
        for (Enemy enemy : gameMap.tout_les_enemy()) {
            int enemyX = enemy.getX() * cellWidth;
            int enemyY = enemy.getY() * cellHeight;

            // Dessinez l'image de l'ennemi à sa position (x, y)
            g.drawImage(enemyImage, enemyX, enemyY, cellWidth, cellHeight, this);
        }
    }
}
