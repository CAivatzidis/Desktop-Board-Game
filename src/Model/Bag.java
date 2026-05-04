package Model;

import Model.Tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private List<Tile> tiles;

    /**
     * Constructor of the Bag
     */
    public Bag() {
        this.tiles = new ArrayList<Tile>();
    }

    /**
     * Accessor
     * Preconditions:
     * NONE
     * PostConditions:
     * @return The tiles list
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * Transformer
     * Preconditions:
     * tiles must not be NULL
     * Postconditions:
     * Sets tiles
     * @param tiles Set Tile of List
     */
    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }


    /**
     * Transformer
     * Preconditions:
     * tiles must not be NULL
     * Postconditions:
     * @param tile Adds tiles to the list
     */
    public void addTile(Tile tile){
        this.tiles.add(tile);
    }

    /**
     * Transformer
     * Preconditions:
     * tile must not be NULL
     * Postconditions:
     * Tile removed from the list
     * @param tile Removes specific tile
     */
    public void removeTile(Tile tile){
        if(getTiles().isEmpty()){
            System.out.println("There is no tile to remove.");
            return;
        }
        this.tiles.remove(tile);
    }
}
