package src.main.java.model.enemies;

import src.main.java.model.Enemy;

public class WeakEnemy extends Enemy {
    public WeakEnemy(int x, int y) {
        super(10, 2, 1, x, y, false);
    }
}