package Model.Character;

import Model.Player;

public class Digger extends Character{
    /**
     * Preconditions:
     * player,image,ability,color must not be NULL
     * is_used must be 0 or 1
     * Postconditions:
     * Object Digger is created
     * Constructor Of class Digger
     * @param player the player that uses the card
     * @param image the image is used for the card
     * @param ability the ability of the card
     * @param is_used If it's used or Not
     */
    public Digger(Player player, String image, String ability, int is_used, String color , int counter){
        super(player,image,ability,is_used, color , counter);
    }
}
