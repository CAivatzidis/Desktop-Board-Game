package Model.Tile;

public class StatueTile extends FindingTile{
    /**
     * Preconditions:
     * image,category, points must not be NULL
     * Postconditions:
     * Object Statue tile is created
     * Constructor of the Statue Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     */
    public  StatueTile(String image, String category, int points){
        super(image,category,points);
    }
}
