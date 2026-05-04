package Model;

import Model.Tile.*;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Tile> PlayerTiles;
    private int points;
    private List<Model.Character.Character> cards;
    private String color;
    private int turn;
    private String music;
    private int draw_count;
    private String area;
    private String ability_used;
    private int using_card;
    private int sphinx_counter;
    private int caryarid_counter;


    /**
     * Constructor of Player
     *
     * @param name Player's Name
     * @param color Player's Color
     * @param music Path to player's music file
     * Preconditions:
     * name, color, music must not be null
     * PostConditions:
     * All fields are initialized with default values
     */
    public Player(String name, String color, String music) {
        this.name = name;
        this.PlayerTiles = new ArrayList<Tile>();
        this.points = 0;
        this.cards = new ArrayList<Model.Character.Character>();
        this.color = "";
        this.turn = -1;
        this.music = music;
        this.draw_count = 0;
        this.area = "";
        this.ability_used = "";
        this.using_card = 0;
        this.sphinx_counter = 0;
        this.caryarid_counter = 0;


    }

    /**
     * Transformer
     * @param counter New sphinx counter value
     * Preconditions:
     * counter >= 0
     * Postconditions:
     * sphinx_counter is updated
     */
    public void set_sphinx_counter(int counter) {
        this.sphinx_counter = counter;
    }

    /**
     * Transformer
     * @param counter New caryatid counter value
     * Preconditions:
     * counter >= 0
     * Postconditions:
     * caryarid_counter is updated
     */
    public void set_caryarid_counter(int counter) {
        this.caryarid_counter = counter;
    }

    /**
     * Accessor
     * @return Current sphinx counter
     * Preconditions: NONE
     * Postconditions: Returns the current sphinx counter value
     */
    public int get_sphinx_counter() {
        return sphinx_counter;
    }


    /**
     * Accessor
     * @return Current caryatid counter
     * Preconditions: NONE
     * Postconditions: Returns the current caryatid counter value
     */
    public int get_caryarid_counter() {
        return caryarid_counter;
    }


    /**
     * Transformer
     * @param using_card 1 if player is using a card ability, 0 otherwise
     * Preconditions:
     * using_card must be 0 or 1
     * Postconditions:
     * using_card flag is updated
     */
    public void set_using_card(int using_card) {
        this.using_card = using_card;
    }


    /**
     * Accessor
     * @return 1 if player is using a card ability, 0 otherwise
     * Preconditions: NONE
     * Postconditions: Returns the current using_card flag
     */
    public int get_using_card() {
        return using_card;
    }



    /**
     * Transformer
     * @param ability_used Name of the ability currently used (or empty string)
     * Preconditions:
     * ability_used must not be null
     * Postconditions:
     * ability_used is updated
     */
    public void set_ability_used(String ability_used) {
        this.ability_used = ability_used;
    }



    /**
     * Accessor
     * @return Name of the currently used ability (or empty string)
     * Preconditions: NONE
     * Postconditions: Returns the current ability_used string
     */
    public String get_ability_used() {
        return ability_used;
    }

    /**
     * Accessor
     *
     * @return The name of the Player
     * Preconditions:
     * NONE
     * Postconditions:
     * return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Transformer
     *
     * @param name The name the player wants to have
     * Preconditions:
     * name must not be NULL
     * Postconditions:
     * the name is set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Transformer
     * @param music Path to the player's music file
     * Preconditions:
     * music must not be null
     * Postconditions:
     * Player's music path is updated
     */
    public void set_music(String music) {
        this.music = music;
    }


    /**
     * Accessor
     * @return Path to the player's music file
     * Preconditions: NONE
     * Postconditions: Returns the music path
     */
    public String get_music() {
        return music;
    }


    /**
     * Accessor
     * @return Currently selected area (or empty string)
     * Preconditions: NONE
     * Postconditions: Returns the selected area
     */
    public String get_area() {
        return area;
    }


    /**
     * Transformer
     * @param area Area selected by the player
     * Preconditions:
     * area must not be null
     * Postconditions:
     * Selected area is updated
     */
    public void set_area(String area) {
        this.area = area;
    }



    /**
     * Accessor
     * @return Number of tiles drawn this turn
     * Preconditions: NONE
     * Postconditions: Returns the draw count
     */
    public int get_count() {
        return draw_count;
    }


    /**
     * Transformer
     * @param count Number of tiles drawn this turn
     * Preconditions:
     * count >= 0
     * Postconditions:
     * Draw count is updated
     */
    public void set_count(int count) {
        this.draw_count = count;
    }

    /**
     * Accessor
     * @return The list of players Tiles
     * Preconditions:
     * NONE
     * Postconditions:
     * returns the list of players tiles
     */
    public List<Tile> getPlayerTiles() {
        return PlayerTiles;
    }


    /**
     * Transformer
     *
     * @param PlayerTiles Set the list of Player's Tiles
     * Preconditions:
     * Player_tiles must not be NULL
     * Postconditions:
     * Player's Tiles are set
     */
    public void setPlayerTiles(List<Tile> PlayerTiles) {

        this.PlayerTiles = PlayerTiles;
    }


    /**
     * Transformer
     * @param PlayerTile Tile to add to player's collection
     * Preconditions:
     * PlayerTile must not be null
     * Postconditions:
     * Tile is added to player's collection
     */
    public void addPlayerTile(Tile PlayerTile) {
        PlayerTiles.add(PlayerTile);
    }


    /**
     * Accessor
     *
     * @return Player's Points
     * Preconditions:
     * NONE
     * Postconditions:
     * Return Player's points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Transformer
     *
     * @param points Sets Player's Points
     * Preconditions:
     * points must be >=0
     * Postconditions:
     * Player's points set
     */
    public void setPoints(int points) {
        this.points = points;
    }


    /**
     * Transformer
     *
     * @param card Card to add
     *Preconditions:
     *card must not be NULL
     *Postconditions:
     *card is added to cards
     */
    public void AddCard(Model.Character.Character card) {
        this.cards.add(card);

    }

    /**
     * Accessor
     *
     * @return Player's Cards
     */
    public List<Model.Character.Character> getCards() {
        return cards;
    }

    /**
     * Transformers
     *
     * @param cards Sets Player's Cards
     * Preconditions:
     * cards must not be NULL
     * Postconditions:
     * Player's Cards are set
     */
    public void setCards(List<Model.Character.Character> cards) {
        this.cards = cards;
    }


    /**
     * Transformer
     *
     * @param card Removes a specific card
     * Preconditions:
     * card must not be NULL
     * Postconditions:
     * Card removed
     */
    public void RemoveCard(Model.Character.Character card) {
        if (cards.isEmpty()) {
            System.out.println("Cards are empty");
            return;
        } else {
            this.cards.remove(card);
        }
    }

    /**
     * Transformer
     * Preconditions:
     * tile must not be NULL
     * Postconditions:
     * Tiled Added
     *
     * @param tile Adds a specific tile
     */
    public void AddTiles(Tile tile) {
        this.PlayerTiles.add(tile);
    }


    /**
     * Transformer
     * Preconditions:
     * tile must not be NULL
     * Postconditions:
     * Tile removed
     *
     * @param tile Removes a specific tile
     */
    public void RemoveTiles(Tile tile) {
        if (PlayerTiles.isEmpty()) {
            System.out.println("Tiles are empty");
            return;
        }
        this.PlayerTiles.remove(tile);
    }


    /**
     * Accessor
     *
     * @return Player's Color
     */
    public String get_Color() {
        return color;
    }


    /**
     * Transformer
     * Preconditions:
     * color must not be NULL
     * Postconditions:
     * the color is set
     *
     * @param color Sets the color of the player
     */
    public void set_Color(String color) {
        this.color = color;
    }


    /**
     * Accessor
     *
     * @return The Player's turn
     */
    public int getTurn() {
        return turn;
    }


    /**
     * Transformer
     * Preconditions:
     * turn must be >=1
     * Postconditions:
     * Turn is set
     *
     * @param turn Sets Player's Turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Accessor
     * Calculates Amphora Tiles Points
     * Preconditions:
     * PlayerTiles must not be NULL
     * Postconditions:
     * Returns calculated amphora points
     */
    public int Calculate_AmphoraPoints() {
        int blue_amphora = 0;
        int green_amphora = 0;
        int red_amphora = 0;
        int yellow_amphora = 0;
        int brown_amphora = 0;
        int purple_amphora = 0;

        for (int i = 0; i < PlayerTiles.size(); i++) {
            Tile tile = PlayerTiles.get(i);
            if(tile instanceof AmphoraTile) {
                String color = ((AmphoraTile) tile).getColor();

                switch (color) {
                    case "Blue": blue_amphora++; break;

                    case "Green": green_amphora++; break;

                    case "Red": red_amphora++; break;

                    case "Yellow": yellow_amphora++; break;

                    case "Brown": brown_amphora++; break;

                    case "Purple": purple_amphora++; break;

                }
            }
        }

        int points=0;
        while(true){

            int different_amphora=0;

            if(blue_amphora>0) {
                different_amphora++;
                blue_amphora--;
            }
            if(green_amphora>0) {
                different_amphora++;
                green_amphora--;
            }
            if(red_amphora>0) {
                different_amphora++;
                red_amphora--;
            }
            if(yellow_amphora>0) {
                different_amphora++;
                yellow_amphora--;
            }
            if(brown_amphora>0) {
                different_amphora++;
                brown_amphora--;
            }
            if(purple_amphora>0) {
                different_amphora++;
            }
            if(different_amphora<3){
                break;
            }


            if(different_amphora== 6){
                points+=6;

            }else if(different_amphora== 5){
                points+=4;

            }else if(different_amphora== 4){
                points+=2;

            }else if(different_amphora == 3){
                points+=1;
            }

        }
        return points;
    }

    /**
     * Accessor
     * Calculates Statue Tiles Points
     * Preconditions:
     * PlayerTiles must not be NULL
     * Postconditions:
     * Returns calculated amphora points
     */
    public int Calculate_StatuePoints() {
        return 0;

    }

    /**
     * Accessor
     * Calculates Skeleton Tiles Points
     * Preconditions:
     * PlayerTiles must not be NULL
     * Postconditions:
     * Returns calculated skeleton points
     */
    public int Calculate_SkeletonPoints() {
        int adult_tops = 0;
        int adult_bottoms = 0;
        int child_tops = 0;
        int child_bottoms = 0;
        for (Tile tile : PlayerTiles) {
            if (tile instanceof SkeletonTile) {
                SkeletonTile skeleton = (SkeletonTile) tile;

                if (skeleton.getSize() == 1) {
                    if (skeleton.getBody_type() == 0) {
                        adult_tops++;

                    } else {
                        adult_bottoms++;
                    }
                } else {
                    if (skeleton.getBody_type() == 0) {
                        child_tops++;

                    } else {
                        child_bottoms++;
                    }
                }
            }
        }

        int complete_adult = Math.min(adult_tops , adult_bottoms);
        int complete_child = Math.min(child_tops , child_bottoms);

        int points =0;

        int families = Math.min(complete_adult/2, complete_child);
        points+=families*6;

        int remaining_adults = complete_adult - families*2;
        int remaining_children = complete_child - families;

        points+=remaining_adults+remaining_children;

        return points;
    }


    /**Accessor
     * Calculates Mosaic Tiles Points
     * Preconditions:
     * PlayerTiles must not be NULL
     * Postconditions:
     * Returns calculated mosaic points
     */
    public int Calculate_MosaicPoints(){

        int points=0;
        int green_count=0;
        int red_count=0;
        int yellow_count=0;

        for(Tile tile : PlayerTiles){
            if(tile instanceof MosaicTile){
                MosaicTile mosaic = (MosaicTile) tile;
                String color =  mosaic.getColor();

                if(color.equals("Green")){
                    green_count++;
                }
                else if(color.equals("Red")){
                    red_count++;
                }
                else if(color.equals("Yellow")){
                    yellow_count++;
                }

            }
        }

        int green_sets= green_count /4;
        points+=green_sets*4;
        int remaining_green = green_count %4;

        int red_sets = red_count /4;
        points+=red_sets*4;
        int remaining_red = red_count %4;

        int yellow_sets = yellow_count /4;
        points+=yellow_sets*4;
        int remaining_yellow = yellow_count %4;

        while(remaining_green + remaining_red + remaining_yellow >=4){
            int different_colors =0;

            if(remaining_green >0){
                different_colors++;
                remaining_green--;
            }
            if(remaining_red >0){
                different_colors++;
                remaining_red--;
            }
            if(remaining_yellow >0){
                different_colors++;
                remaining_yellow--;
            }

            if(different_colors>=3){

                if(remaining_green>0){
                    remaining_green--;
                }
                else if(remaining_red>0){
                    remaining_red--;
                }
                else if (remaining_yellow>0) {
                    remaining_yellow--;
                }
                points+=2;

            }else{
                break;
            }
        }
        return points;

    }


    /**Accessor
     * Calculates player's sum of points
     * Preconditions:
     * PlayerTiles must not be NULL
     * Postconditions:
     * returns calculated Sumpoints of all the tiles
     */
    public int SumPoints(){

        points+=Calculate_SkeletonPoints();
        points+=Calculate_MosaicPoints();
        points+=Calculate_AmphoraPoints();
        return points;

    }



}
