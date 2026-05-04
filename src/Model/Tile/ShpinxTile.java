package Model.Tile;

public class ShpinxTile extends StatueTile{
    /**
     * Preconditions:
     * image,category, points must not be NULL
     * Postconditions:
     * Object Sphinx tiles is created
     * Constructor of the Sphinx Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     */
    public  ShpinxTile(String image, String category, int points){
        super(image,category,points);
    }
}
