package Model.Tile;

public class SkeletonTile extends FindingTile {
    private int body_type;
    private int size;



    /**
     * Preconditions:
     * image,category,points must not be NULL
     * size , body_type must be 0 or 1
     * (0 = small) (1 = big) (0 = Upper) (1 = Lower)
     * Postconditions:
     * Object Skeleton Tile is created
     * Constructor of the Skeleton Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     */
    public  SkeletonTile(String image, String category, int points, int size , int body_type){
        super(image,category,points);
        this.body_type=body_type;
        this.size=size;
    }


    /**
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns whether is UpperBody or LowerBody
     * Accessor
     * @return whether is UpperBody skeleton tile or LowerBody
     */
    public int getBody_type() {
        return body_type;
    }

    /**
     * Preconditions:
     * body_type must be 0 or 1
     * Postconditions:
     * body_type is set
     * Transformer
     * @param body_type Sets the body type of the skeleton tile 1 Upper 0 Lower
     */
    public void setBody_type(int body_type) {
        this.body_type = body_type;
    }

    /**
     * Accessor
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the size of the skeleton
     * @return The size of skeleton tile. Big or Small
     */
    public int getSize() {
        return size;
    }

    /**
     * Preconditions:
     * size must be 0 or 1
     * Postconditions:
     * size is set
     * Transformer
     * @param size sets the size of the skeleton tile. Big or Small
     */
    public void setSize(int size) {
        this.size = size;
    }
}
