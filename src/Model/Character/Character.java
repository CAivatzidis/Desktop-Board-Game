package Model.Character;

import Model.Player;

public abstract class Character {
    private Player player;
    private String image;
    private String ability;
    private int is_used;
    private String color;
    private int counter;
    private int selected_area;

    /**
     * Preconditions:
     * player must not be NULL
     * image must not be NULL
     * ability must not be NULL
     * is_used must be 1 or 0
     * color must not be NULL
     * Postconditions:
     * Player is set
     * image is set
     * ability is set
     * color is set
     * Constructor Of class Character
     * @param player the player that uses the card
     * @param image the image is used for the card
     * @param ability the ability of the card
     * @param is_used If it's used or Not
     */
    public Character(Player player, String image, String ability, int is_used,  String color, int counter) {
        this.player = player;
        this.image = image;
        this.ability = ability;
        this.is_used = is_used;
        this.color = color;
        this.counter = counter;
        this.selected_area = 0;

    }



    public int get_counter() {
        return counter;
    }

    public void set_counter(int counter) {
        this.counter = counter;
    }


    public int getSelected_area() {
        return selected_area;
    }

    public void setSelected_area(int selected_area) {
        this.selected_area = selected_area;
    }




    /**
     * Accessor
     * Preconditions:
     * NONE
     * Postconditions:
     * returns the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Transformer
     * Preconditions:
     * player must not be NULL
     * Postconditions:
     * player is set
     * @param player Sets The player
     *
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Accessor
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the image path
     * @return Gets image path
     */
    public String getImage() {
        return image;
    }

    /**
     * Transformer
     * Preconditions:
     * image must not be NULL
     * Postconditions:
     * image path is set
     * @param image Sets ImagePath
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Accessor
     * Preconditions:
     * abilitymust not be NULL
     * Postconditions:
     * Returns the ability
     * @return ability
     */
    public String getAbility() {
        return ability;
    }

    /**
     * Transformer
     * Preconditions:
     * ability must not be NULL
     * Postconditions:
     * Ability is set
     * @param ability Sets The ability
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }

    /**
     * Accessor
     * Preconditions:
     * NONE
     * Postconditions:
     * returns if the card is used
     * @return if the card is used
     */
    public int getIs_used() {
        return is_used;
    }

    /**
     * Transformer
     * Preconditions:
     * is_used must be 0 or 1
     * Postconditions:
     * is_used is set
     * @param is_used Sets the card Used or Not
     */
    public void setIs_used(int is_used) {
        this.is_used = is_used;
    }

    /**
     * Accessor
     * @return The color of the Card
     * Preconditions:
     * NONE
     * Postconditions:
     * Returns the color of the card
     */
    public String getColor() {
        return color;
    }


    /**
     * Transformer
     * Preconditions:
     * color must not be NULL
     * Postconditions:
     * color is set
     * @param color Sets the color of the card
     */
    public void setColor(String color) {
        this.color = color;
    }


}

