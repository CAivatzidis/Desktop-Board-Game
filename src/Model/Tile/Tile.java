package Model.Tile;

public abstract class Tile {
    private String image;
    private String category;
    private int points;


    /**
     * Constructor of Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     * Preconditions:
     * image must not be NULL
     * category must not be NULL
     * points must be >=0
     * Postconditions:
     * Tile fields are initialized
     */
    public Tile(String image, String category, int points){
        this.image = image;
        this.category = category;
        this.points = points;
    }

    /**
     * Accessor
     * @return The image Path
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the image path
     */
    public String getImage() {
        return image;
    }

    /**
     * Transformer
     * @param image Sets the image path
     * Preconditions:
     * image must not be NULL
     * Postconditions:
     * Sets the image path
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Accessor
     * @return The category of the Tile
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the category of the Tile
     */
    public String getCategory() {
        return category;
    }

    /**
     * Transformer
     * @param category Sets the category of the Tile
     * Preconditions:
     * Category must not be NULL
     * Postconditions:
     * The category of the tile is set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Accessor
     * @return The points of the Tile
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the points of the Tile
     */
    public int getPoints() {
        return points;
    }

    /**
     * Transformer
     * @param points Sets the Points of the Tile
     * Preconditions:
     * points must be >=0
     * Postconditions:
     * The points of the tile is set
     */
    public void setPoints(int points) {
        this.points = points;
    }

}
