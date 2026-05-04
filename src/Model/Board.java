package Model;

import Model.Tile.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<LandSlideTile> land_slide_field;
    private List<AmphoraTile> amphora_field;
    private List<StatueTile> statue_field;
    private List<MosaicTile> mosaic_field;
    private List<SkeletonTile> skeleton_field;
    private String Background_Image;



    /**
     * Constructor of The Board
     * Preconditions:
     * NONE
     * Postconditions:
     * All field are initialized
     */
    public Board(){
        land_slide_field = new ArrayList<>();
        this.amphora_field = new ArrayList<>();
        this.statue_field = new ArrayList<>();
        this.mosaic_field = new ArrayList<>();
        this.skeleton_field = new ArrayList<>();

    }

                                                    //Land Slide Tiles
    /**
     * Accessor
     * @return The Field of the landSlide
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the field of the landslide
     */
    public List<LandSlideTile> getLand_slide_field() {
        return land_slide_field;
    }

    /**
     * Transformer
     * @param land_slide_field Sets the land_slide field
     * Preconditions:
     * land_slide_field must not be NULL
     * Postconditions:
     * land_slide field is updated
     */
    public void setLand_slide_field(List<LandSlideTile> land_slide_field) {
        this.land_slide_field = land_slide_field;
    }


    public void add_Land_slide_tile(Tile tile){
        land_slide_field.add((LandSlideTile)tile);
    }
                                                    //Amphora Tiles
    /**
     * Accessor
     * @return The Amphora Tiles Field
     * Preconditions:
     * NONE
     * Postconditions:
     * returns the Amphora tiles Field
     */
    public List<AmphoraTile> getAmphora_field() {
        return amphora_field;
    }

    /**
     * Transformer
     * @param amphora Adds an Amphora Tile
     * Preconditions:
     * amphora must not be NULL
     * Postconditions:
     * Amphora tile added
     */
    public void AddAmphoraTile(AmphoraTile amphora){
        amphora_field.add(amphora);
    }

    /**
     * Transformer
     * @param amphora Removes a specific Amphora Tile
     * Preconditions:
     * amphora_field mus t not be empty
     * Postconditions:
     * Amphora tile is removed from the field
     */
    public void removeAmphoraTile(AmphoraTile amphora){
        if(amphora_field.isEmpty()){
            System.out.println("Amphora_field is empty");
        }else {
            amphora_field.remove(amphora);
        }
    }

                                                    //Statue Tiles

    /**
     * Accessor
     * @return the Statues tile Field
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the statue tile field
     */
    public List<StatueTile> getStatue_field() {
        return statue_field;
    }

    /**
     * Transformer
     * @param statue Adds a Statue Tile
     *Preconditions:
     * statue!=NULL
     *Postconditions:
     *statue tile is added to Statue field
     */
    public void AddStatueTile(StatueTile statue){
        statue_field.add(statue);
    }


    /**
     * Transformer
     * @param statue Removes a specific Statue Tile
     * Preconditions:
     * statue_field must not be empty
     * Postconditions:
     * statue removed
     */
    public void removeStatueTile(StatueTile statue){
        if(statue_field.isEmpty()){
            System.out.println("Statue_field is empty");
        }else {
            statue_field.remove(statue);
        }
    }


                                                    //Mosaic Tiles
    /**
     * Accessor
     * @return The Mosaic Tile Field
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the Mosaic Tile Field
     */
    public List<MosaicTile> getMosaic_field() {
        return mosaic_field;
    }

    /**
     * Transformer
     * @param mosaic Adds a Mosaic Tile
     * Preconditions:
     * mosaic must not be NULL
     * Postconditions:
     * Mosaic Tile Added
     */
    public void AddMosaicTile(MosaicTile mosaic){
        mosaic_field.add(mosaic);
    }

    /**
     * Transformer
     * @param mosaic Removes a specific Mosaic Tile
     * Preconditions:
     * Mosaic_field is not empty
     * Postconditions:
     * Mosaic Field is updated
     */
    public void removeMosaicTile(MosaicTile mosaic){
        if(mosaic_field.isEmpty()){
            System.out.println("Mosaic_field is empty");
        }else {
            mosaic_field.remove(mosaic);
        }
    }



                                                        //Skeleton Tiles
    /**
     * Accessor
     * @return The Skeleton Tile Field
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the Skeleton Tile Field
     */
    public List<SkeletonTile> getSkeleton_field() {
        return skeleton_field;
    }

    /**
     * Transformer
     * @param skeleton Adds a Skeleton Tile
     * Preconditions:
     * skeleton must not be NULL
     * Postconditions:
     * Skeleton tile Added
     */
    public void AddSkeletonTile(SkeletonTile skeleton){
        skeleton_field.add(skeleton);
    }

    /**
     * Transformer
     * @param skeleton Removes a specific Skeleton Tile
     * Preconditions:
     * Skeleton_field must not be empty
     * Preconditions:
     * Skeleton Tile is removed
     */
    public void removeSkeletonTile(SkeletonTile skeleton){
        if(skeleton_field.isEmpty()){
            System.out.println("Skeleton_field is empty");
        }else {
            skeleton_field.remove(skeleton);
        }
    }


}
