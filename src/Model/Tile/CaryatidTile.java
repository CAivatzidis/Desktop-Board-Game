package Model.Tile;

public class CaryatidTile extends StatueTile {
    /**
     * Preconditions:
     * image,category, points must not be NULL
     * Postconditions:
     * Object Caryatid Tile is created
     * Constructor of the Caryatid Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     */
    public  CaryatidTile(String image, String category, int points){
        super(image,category,points);
    }
}
