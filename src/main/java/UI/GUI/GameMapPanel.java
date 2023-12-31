package src.main.java.UI.GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.main.java.configMap.GameMap;
import src.main.java.model.*;
import src.main.java.model.Tower;

public class GameMapPanel extends JPanel {

    private GameMap gameMap;
    private Image towerImage;
    private Map<String, Image> enemysImages;
    private Image backgroundImage; // Image de fond des cellules
    private int cellWidth; // Largeur des cellules
    private int cellHeight;

    public GameMapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        enemysImages = new HashMap<String,Image>();
        
        try {
            // Charger les images des tours et des ennemis
            towerImage = ImageIO.read(new File("pack/towers/tempo.png"));
            Image enemyImage1 = ImageIO.read(new File("pack/enemies/enemy1/1_enemies_1_run_000.png"));
            Image enemyImage2 = ImageIO.read(new File("pack/enemies/enemy2/2_enemies_1_attack_000.png"));
            Image enemyImage3 = ImageIO.read(new File("pack/enemies/enemy3/8_enemies_1_attack_000.png"));
            Image enemyImage4 = ImageIO.read(new File("pack/enemies/enemy4/9_enemies_1_attack_000.png"));
            backgroundImage = ImageIO.read(new File("pack/buttons/pause.png"));

            enemysImages.put("MediumEnemy", enemyImage4);
            enemysImages.put("WeakEnemy", enemyImage1);
            enemysImages.put("RangeEnemy", enemyImage3);
            enemysImages.put("StringEnemy", enemyImage2);

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

            if(enemy instanceof MediumEnemy) g.drawImage(enemysImages.get("MediumEnemy"), enemyX, enemyY, cellWidth, cellHeight, this);
            if(enemy instanceof StrongEnemy) g.drawImage(enemysImages.get("StrongEnemy"), enemyX, enemyY, cellWidth, cellHeight, this);
            if(enemy instanceof WeakEnemy) g.drawImage(enemysImages.get("WeakEnemy"), enemyX, enemyY, cellWidth, cellHeight, this);
            if(enemy instanceof RangeEnemy) g.drawImage(enemysImages.get("RangeEnemy"), enemyX, enemyY, cellWidth, cellHeight, this);
            
        }
    }
}
