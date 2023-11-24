public class GameMap {

    public Cellule[][] tiles;

    public GameMap(int rows, int cols) {

        this.tiles = new Cellule[rows][cols];

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {
                
                tiles[i][j] = new Cellule();

            }
        }
    }


    public void affiche(){

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                tiles[i][j].affiche();
                
            }
            
        }


    }

    // Methods for interacting with tiles, placing towers, moving enemies, etc.
}