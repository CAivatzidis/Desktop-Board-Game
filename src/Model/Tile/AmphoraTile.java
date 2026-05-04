package Model.Tile;

public class AmphoraTile extends FindingTile{

    private String color;

    /**
     * Preconditions:
     * image,category, points, color must not be NULL
     * Postconditions:
     * Object Amphora tile is created
     * Constructor of the Amphora Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     */
    public  AmphoraTile(String image, String category, int points , String color) {
        super(image,category,points);
        this.color=color;
    }

    /**
     * Preconditions:
     * color must not be NULL
     * Postconditions:
     * Returns the color of the Amphora tile
     * Accessor
     * @return The color of the Amphora tile
     */
    public String getColor() {
        return color;
    }

    /**
     * Preconditions:
     * NONE
     * Postconditions:
     * The color is set
     * Transformer
     * @param color Sets the color of the Amphora Tile
     */
    public void setColor(String color) {
        this.color = color;
    }

}
