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

    public boolean placer(Element e, int x, int y){

        if(tiles[y][x].get_elt() == null){

            tiles[y][x].set_elt(e);
            return true;

        }

        return false;

    }

    public boolean deplacer(int x1, int y1, int x2, int y2){

        if(tiles[y1][x1].get_elt() == null || tiles[y2][x2].get_elt() != null){

            return false;

        } else {

            tiles[y2][x2].set_elt(tiles[y1][x1].get_elt());
            tiles[y1][x1].set_elt(null);
            return true;

        }


    }


    public void affiche(){

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[0].length; j++) {

                tiles[i][j].affiche();
                
            }

            System.out.println();
            
        }


    }

    // Methods for interacting with tiles, placing towers, moving enemies, etc.
}