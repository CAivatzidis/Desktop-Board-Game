package Model.Tile;

public class MosaicTile extends FindingTile{

    private String color;
    /**
     * Preconditions:
     * image,category, points, color,must not be NULL
     * Postconditions:
     * Object Mosaic Tile is created
     * Constructor of the Mosaic Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     */
    public MosaicTile(String image, String category, int points , String color) {
        super(image,category,points);
        this.color = color;
    }

    /**
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the color of the mosaic Tile
     * Accessor
     * @return the color of the Mosaic Tile
     */
    public String getColor() {
        return color;
    }

    /**
     * Preconditions:
     * color must not be NULL
     * Postconditions:
     * color is set
     * Transformer
     * @param color sets the color of the Mosaic Tile
     */
    public void setColor(String color) {
        this.color = color;
    }
}
