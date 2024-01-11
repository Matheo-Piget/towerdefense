package src.main.java.UI.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import src.main.java.configMap.GameMap;
import src.main.java.model.*;
import src.main.java.model.enemies.*;
import src.main.java.model.towers.*;
import src.main.java.start.Player;

/**
 * Panel représentant la carte de jeu et gérant les interactions avec celle-ci.
 */
public class GameMapPanel extends JPanel {

    private GameMap gameMap;
    private int highlightedCellX = -1;
    private int highlightedCellY = -1;
    private Map<String, Image> enemiesImages;
    private Map<String, Image> towerImages;
    private Image grass;
    private int cellWidth;
    private Player player;
    private int cellHeight;
    private String towerToPlace;
    private boolean isPlacingTower = false;

    /**
     * Constructeur de GameMapPanel prenant la carte de jeu comme argument.
     * Initialise les images, la carte et les écouteurs de la souris.
     * 
     * @param gameMap La carte de jeu.
     */
    public GameMapPanel(GameMap gameMap, Player p) {
        this.gameMap = gameMap;
        this.player = p;
        enemiesImages = new HashMap<>();
        towerImages = new HashMap<>();
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

            Image tntTower = ImageIO.read(new File("src/main/ressources/towers/tnttower.png"));
            Image fightTower = ImageIO.read(new File("src/main/ressources/towers/fighttower.png"));
            Image sniperTower = ImageIO.read(new File("src/main/ressources/towers/snipertower.png"));
            Image nukeTower = ImageIO.read(new File("src/main/ressources/towers/nucleartower.png"));
            Image speedTower = ImageIO.read(new File("src/main/ressources/towers/speedtower.png"));
            Image bulletTower = ImageIO.read(new File("src/main/ressources/towers/bullettower.png"));

            towerImages.put("tntTower", tntTower);
            towerImages.put("fightTower", fightTower);
            towerImages.put("sniperTower", sniperTower);
            towerImages.put("nukeTower", nukeTower);
            towerImages.put("speedTower", speedTower);
            towerImages.put("bulletTower", bulletTower);

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
                // Obtenez le coût de la tour à placer
                int towerCost = getTowerCost(towerToPlace);

                // Vérifiez si le joueur a suffisamment d'argent pour placer la tour
                if (towerCost <= player.getMoney()) {
                    // Placez la tour car le joueur a assez d'argent
                    placeTower(cellX, cellY);
                    player.setMoney(player.getMoney() - towerCost); // Déduisez le coût de la tour de l'argent du joueur
                } else {

                    return;
                }
            }

        }
        repaint();
    }

    // Méthode pour obtenir le coût d'une tour en fonction de son type
    private int getTowerCost(String towerType) {
        // Mettez en place une logique pour obtenir le coût de chaque type de tour
        // Retournez le coût approprié
        switch (towerType) {
            case "Fight Tower":
                return 40;
            case "Bullet Tower":
                return 50;
            case "Nuke Tower":
                return 300;
            case "Sniper Tower":
                return 80;
            case "Speed Tower":
                return 75;
            case "TnT Tower":
                return 220;

        }
        return 0; // Si le type de tour n'est pas reconnu, retournez 0 ou un autre montant par
                  // défaut
    }

    // Méthode pour placer la tour
    private void placeTower(int cellX, int cellY) {
        switch (towerToPlace) {
            case "Fight Tower":
                gameMap.putElem(new FightTower(cellY, cellX));
                break;
            case "Bullet Tower":
                gameMap.putElem(new BulletTower(cellY, cellX));
                break;
            case "Nuke Tower":
                gameMap.putElem(new NukeTower(cellY, cellX));
                break;
            case "Sniper Tower":
                gameMap.putElem(new SniperTower(cellY, cellX));
                break;
            case "Speed Tower":
                gameMap.putElem(new SpeedTower(cellY, cellX));
                break;
            case "TnT Tower":
                gameMap.putElem(new TntTower(cellY, cellX));
                break;
            default:
                break;
        }
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
        if (cellHeight == 0 || cellWidth == 0)
            return;

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

            }
        }

        // Dessine les tours sur la carte
        for (Tower t : gameMap.listOfAllTowers()) {
            int towerX = t.getX() * cellWidth;
            int towerY = t.getY() * cellHeight;

            if (t instanceof FightTower)
                g.drawImage(towerImages.get("fightTower"), towerX, towerY, cellWidth, cellHeight, this);
            if (t instanceof BulletTower)
                g.drawImage(towerImages.get("bulletTower"), towerX, towerY, cellWidth, cellHeight, this);
            if (t instanceof NukeTower)
                g.drawImage(towerImages.get("nukeTower"), towerX, towerY, cellWidth, cellHeight, this);
            if (t instanceof SniperTower)
                g.drawImage(towerImages.get("sniperTower"), towerX, towerY, cellWidth, cellHeight, this);
            if (t instanceof SpeedTower)
                g.drawImage(towerImages.get("speedTower"), towerX, towerY, cellWidth, cellHeight, this);
            if (t instanceof TntTower)
                g.drawImage(towerImages.get("tntTower"), towerX, towerY, cellWidth, cellHeight, this);
        }

        // Dessine les ennemis sur la carte
        for (Enemy enemy : gameMap.listOfAllEnemies()) {
            int enemyX = enemy.getX() * cellWidth;
            int enemyY = enemy.getY() * cellHeight;

            if (enemy instanceof Dreth)
                g.drawImage(enemiesImages.get("enemyDreth"), enemyX, enemyY, cellWidth, cellHeight, this);
            if (enemy instanceof Zorch)
                g.drawImage(enemiesImages.get("enemyZorch"), enemyX, enemyY, cellWidth, cellHeight, this);
            if (enemy instanceof Fyron)
                g.drawImage(enemiesImages.get("enemyFyron"), enemyX, enemyY, cellWidth, cellHeight, this);
            if (enemy instanceof Gazer)
                g.drawImage(enemiesImages.get("enemyGazer"), enemyX, enemyY, cellWidth, cellHeight, this);
            if (enemy instanceof Kryon)
                g.drawImage(enemiesImages.get("enemyKryon"), enemyX, enemyY, cellWidth, cellHeight, this);
            if (enemy instanceof Liche)
                g.drawImage(enemiesImages.get("enemyLiche"), enemyX, enemyY, cellWidth, cellHeight, this);
        }
        if (highlightedCellX != -1 && highlightedCellY != -1) {
            g.setColor(new Color(255, 255, 0, 100)); // Jaune avec opacité réduite
            g.fillRect(highlightedCellX * cellWidth, highlightedCellY * cellHeight, cellWidth, cellHeight);

        }
    }
}
