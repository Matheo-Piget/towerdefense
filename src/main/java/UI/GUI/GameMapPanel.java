package src.main.java.UI.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private Image grass;
    private int cellWidth;
    private int cellHeight;
    private String towerToPlace;
    private boolean isPlacingTower = false;

    /**
     * Constructeur de GameMapPanel prenant la carte de jeu comme argument.
     * Initialise les images, la carte et les écouteurs de la souris.
     * 
     * @param gameMap La carte de jeu.
     */
    public GameMapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        enemiesImages = new HashMap<>();
        towerToPlace = null;
        isPlacingTower = false;

        initializeImages();
        initializeMouseListeners();
    }

    /**
     * Initialise les images utilisées dans le jeu.
     */
    private void initializeImages() {
        try {
            towerImage = ImageIO.read(new File("src/main/ressources/towers/fighttower.png"));
            grass = ImageIO.read(new File("src/main/ressources/elements/grass.jpg"));

            Image enemyDreth = ImageIO.read(new File("src/main/ressources/mobs/dreth.png"));
            Image enemyFyron = ImageIO.read(new File("src/main/ressources/mobs/fyron.png"));
            Image enemyGazer = ImageIO.read(new File("src/main/ressources/mobs/gazer.png"));
            Image enemyKyron = ImageIO.read(new File("src/main/ressources/mobs/kryon.png"));
            Image enemyLiche = ImageIO.read(new File("src/main/ressources/mobs/liche.png"));
            Image enemyZorch = ImageIO.read(new File("src/main/ressources/mobs/zorch.png"));

            enemiesImages.put("enemyDreth", enemyDreth);
            enemiesImages.put("enemyFyron", enemyFyron);
            enemiesImages.put("enemyGazer", enemyGazer);
            enemiesImages.put("enemyLiche", enemyLiche);
            enemiesImages.put("enemyKyron", enemyKyron);
            enemiesImages.put("enemyZorch", enemyZorch);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise les écouteurs de souris pour les interactions avec la carte.
     */
    private void initializeMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                highlightedCellX = -1;
                highlightedCellY = -1;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                placeTowerAtReleasedPosition(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                updateHighlightedCell(e.getX(), e.getY());
            }
        });
    }

    /**
     * Définit le type de tour à placer.
     * 
     * @param towerType Le type de tour à placer.
     */
    public void setTowerToPlace(String towerType) {
        this.towerToPlace = towerType;
        isPlacingTower = true;
    }

    /**
     * Place la tour à la position relâchée.
     * 
     * @param x Position x relâchée.
     * @param y Position y relâchée.
     */
    public void placeTowerAtReleasedPosition(int x, int y) {
        if (isPlacingTower && towerToPlace != null) {
            int cellX = x / cellWidth;
            int cellY = y / cellHeight;

            if (cellX >= 0 && cellX < gameMap.getCols() && cellY >= 0 && cellY < gameMap.getRows()) {
                // Utilisation du type de tour sélectionné depuis GameState pour placer la tour
                switch (towerToPlace) {
                    case "Tower 1":
                        gameMap.putElem(new Tower(10, 10, 3, cellY, cellX));
                        break;
                    case "Tower 2":
                        gameMap.putElem(new Tower(10, 10, 3, cellY, cellX));
                        break;
                    case "Tower 3":
                        gameMap.putElem(new Tower(10, 10, 3, cellY, cellX));
                        break;
                    case "Tower 4":
                        gameMap.putElem(new Tower(10, 10, 3, cellY, cellX));
                        break;
                    case "Tower 5":
                        gameMap.putElem(new Tower(10, 10, 3, cellY, cellX));
                        break;
                    case "Tower 6":
                        gameMap.putElem(new Tower(10, 10, 3, cellY, cellX));
                        break;
                    default:
                        break;
                }
            }
        }
        repaint();
    }

    /**
     * Annule le placement de la tour.
     */
    public void cancelTowerPlacement() {
        isPlacingTower = false;
    }

    /**
     * Met à jour la surbrillance de la cellule.
     * 
     * @param x Coordonnée x de la souris.
     * @param y Coordonnée y de la souris.
     */
    private void updateHighlightedCell(int x, int y) {
        if (cellHeight == 0 || cellWidth == 0) return;
    
        int cellX = x / cellWidth;
        int cellY = y / cellHeight;

        if (cellX != highlightedCellX || cellY != highlightedCellY) {
            highlightedCellX = cellX;
            highlightedCellY = cellY;
            repaint();
        }
        
    }


    /**
     * Redéfinition de paintComponent pour dessiner les éléments de la carte.
     * 
     * @param g Objet Graphics pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        cellWidth = getWidth() / gameMap.getCols();
        cellHeight = getHeight() / gameMap.getRows();

        for (int i = 0; i < gameMap.getCols(); i++) {
            for (int j = 0; j < gameMap.getRows(); j++) {

                int x = i * cellWidth;
                int y = j * cellHeight;

                g.drawImage(grass, x, y, cellWidth, cellHeight, this);
                //else g.drawImage(dirt, x, y, cellWidth, cellHeight, this);

                
            }
        }

        // Dessine les tours sur la carte
        for (Tower t : gameMap.listOfAllTowers()) {
            int towerX = t.getX() * cellWidth;
            int towerY = t.getY() * cellHeight;

            g.drawImage(towerImage, towerX, towerY, cellWidth, cellHeight, this);
        }

        // Dessine les ennemis sur la carte
        for (Enemy enemy : gameMap.listOfAllEnemies()) {
            int enemyX = enemy.getX() * cellWidth;
            int enemyY = enemy.getY() * cellHeight;

            if (enemy instanceof Enemy)
                g.drawImage(enemiesImages.get("enemyDreth"), enemyX, enemyY, cellWidth, cellHeight, this);
            // if(enemy instanceof StrongEnemy)
            // g.drawImage(enemiesImages.get("StrongEnemy"), enemyX, enemyY, cellWidth,
            // cellHeight, this);
            // if(enemy instanceof WeakEnemy) g.drawImage(enemiesImages.get("WeakEnemy"),
            // enemyX, enemyY, cellWidth, cellHeight, this);
            // if(enemy instanceof RangeEnemy) g.drawImage(enemiesImages.get("RangeEnemy"),
            // enemyX, enemyY, cellWidth, cellHeight, this);
        }
        if (highlightedCellX != -1 && highlightedCellY != -1) {
            g.setColor(new Color(255, 255, 0, 100)); // Jaune avec opacité réduite
            g.fillRect(highlightedCellX * cellWidth, highlightedCellY * cellHeight, cellWidth, cellHeight);
    
        }
    }
}
