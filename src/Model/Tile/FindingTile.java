package Model.Tile;

import Model.Player;

public class FindingTile extends Tile {
    /**
     * Preconditions:
     * image,category, points must not be NULL
     * Postconditions:
     * Object Finding_tile is created
     * Constructor of the Finding Tile
     * @param image image path
     * @param category the category of the tile
     * @param points tile's points
     */
    public  FindingTile(String image, String category, int points){
        super(image,category,points);
    }
}
