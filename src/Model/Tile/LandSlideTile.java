package Model.Tile;

public class LandSlideTile extends Tile {
    /**
     * Preconditions:
     * image,category, points must not be NULL
     * Postconditions:
     * Object LandSlideTile is created
     * Constructor of the LandSlide Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     */
    public LandSlideTile(String image, String category, int points){
        super(image,category,points);
    }
}
