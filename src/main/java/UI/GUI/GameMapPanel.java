package src.main.java.UI.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import src.main.java.configMap.GameMap;
import src.main.java.model.*;

/**
 * Panel représentant la carte de jeu et gérant les interactions avec celle-ci.
 */
public class GameMapPanel extends JPanel {

    private GameMap gameMap;
    private Image towerImage;
    private int highlightedCellX = -1;
    private int highlightedCellY = -1;
    private Map<String, Image> enemiesImages;
    private Image backgroundImage;
    private int cellWidth;
    private int cellHeight;
    private String towerToPlace;
    private boolean isPlacingTower = false;

    /**
     * Constructeur de GameMapPanel prenant la carte de jeu comme argument.
     * Initialise les images, la carte et les écouteurs de la souris.
     * @param gameMap La carte de jeu.
     */
    public GameMapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        enemiesImages = new HashMap<String, Image>();
        towerToPlace = null;
        isPlacingTower = false;

        // Ajoute un écouteur pour gérer la surbrillance lors du survol des cellules
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                highlightedCellX = -1;
                highlightedCellY = -1;
                repaint();
            }
        });

        // Ajoute un écouteur pour mettre en surbrillance la cellule survolée
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int cellX = e.getX() / cellWidth;
                int cellY = e.getY() / cellHeight;

                if (cellX != highlightedCellX || cellY != highlightedCellY) {
                    highlightedCellX = cellX;
                    highlightedCellY = cellY;
                    repaint();
                }
            }
        });

        try {
             towerImage = ImageIO.read(new File("pack/towers/tempo.png"));
             Image enemyImage1 = ImageIO.read(new File("pack/enemies/enemy1/1_enemies_1_run_000.png"));
             Image enemyImage2 = ImageIO.read(new File("pack/enemies/enemy2/2_enemies_1_attack_000.png"));
             Image enemyImage3 = ImageIO.read(new File("pack/enemies/enemy3/8_enemies_1_attack_000.png"));
             Image enemyImage4 = ImageIO.read(new File("pack/enemies/enemy4/9_enemies_1_attack_000.png"));
             backgroundImage = ImageIO.read(new File("pack/buttons/pause.png"));
 
             enemiesImages.put("MediumEnemy", enemyImage4);
             enemiesImages.put("WeakEnemy", enemyImage1);
             enemiesImages.put("RangeEnemy", enemyImage3);
             enemiesImages.put("StringEnemy", enemyImage2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Définit le type de tour à placer.
     * @param towerType Le type de tour à placer.
     */
    public void setTowerToPlace(String towerType) {
        this.towerToPlace = towerType;
        isPlacingTower = true;
    }

    /**
     * Place la tour à la position relâchée.
     * @param x Position x relâchée.
     * @param y Position y relâchée.
     */
    public void placeTowerAtReleasedPosition(int x, int y) {
        if (isPlacingTower) {
            int cellX = x / cellWidth;
            int cellY = y / cellHeight;

            if (cellX >= 0 && cellX < gameMap.getCols() && cellY >= 0 && cellY < gameMap.getRows()) {
                gameMap.placer(new WeakTower(cellX, cellY));
                repaint();
            }

            isPlacingTower = false;
        }
    }

    /**
     * Annule le placement de la tour.
     */
    public void cancelTowerPlacement() {
        isPlacingTower = false;
    }

    

    /**
     * Redéfinition de la méthode paintComponent pour dessiner les éléments de la carte.
     * @param g Objet Graphics pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        cellWidth = getWidth() / gameMap.getCols();
        cellHeight = getHeight() / gameMap.getRows();

        // Dessine l'arrière-plan de la carte
        for (int i = 0; i < gameMap.getCols(); i++) {
            for (int j = 0; j < gameMap.getRows(); j++) {
                int x = i * cellWidth;
                int y = j * cellHeight;

                g.drawImage(backgroundImage, x, y, cellWidth, cellHeight, this);
            }
        }

        // Dessine les tours sur la carte
        for (Tower t : gameMap.tout_les_tower()) {
            int towerX = t.getX() * cellWidth;
            int towerY = t.getY() * cellHeight;

            g.drawImage(towerImage, towerX, towerY, cellWidth, cellHeight, this);
        }
        
        // Dessine les ennemis sur la carte
        for (Enemy enemy : gameMap.tout_les_enemy()) {
            int enemyX = enemy.getX() * cellWidth;
            int enemyY = enemy.getY() * cellHeight;

            if(enemy instanceof MediumEnemy) g.drawImage(enemiesImages.get("MediumEnemy"), enemyX, enemyY, cellWidth, cellHeight, this);
            if(enemy instanceof StrongEnemy) g.drawImage(enemiesImages.get("StrongEnemy"), enemyX, enemyY, cellWidth, cellHeight, this);
            if(enemy instanceof WeakEnemy) g.drawImage(enemiesImages.get("WeakEnemy"), enemyX, enemyY, cellWidth, cellHeight, this);
            if(enemy instanceof RangeEnemy) g.drawImage(enemiesImages.get("RangeEnemy"), enemyX, enemyY, cellWidth, cellHeight, this);
        }

        // Si une cellule est en surbrillance, la dessine en jaune avec une transparence réduite
        if (highlightedCellX != -1 && highlightedCellY != -1) {
            g.setColor(new Color(255, 255, 0, 100)); // Jaune avec opacité réduite
            g.fillRect(highlightedCellX * cellWidth, highlightedCellY * cellHeight, cellWidth, cellHeight);
        }
    }
}
