package src.main.java.UI;

import src.main.java.configMap.GameMap;
import src.main.java.start.Player;

public class TerminalUI {

    public void startTerminalGame(GameMap map, Player player) {

        player.affiche();
        map.affiche();

    }

}
