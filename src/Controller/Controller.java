package Controller;

import Model.Bag;
import Model.Board;
import Model.Character.*;
import Model.Tile.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Model.Character.Character;
import Model.Player;
import javax.sound.sampled.*;
import java.util.Timer;

public class Controller {
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player thief;
    private List<Player> players = new ArrayList<>();
    private Bag bag;
    private Board board;
    private Player currentPlayer;
    private Player winner;
    private Timer timer;
    private int player_num;
    private Clip player_music;



    /**
     * Accessor
     * @return The number of players
     * Postconditions:
     * -Gives access to the number of players
     */
    public int get_PlayerNum(){
        return player_num;

    }

    /**
     * Transformer
     * preconditions:
     * @param player_num must be between 1 and 4
     * Postconditions:
     * returns the number of players
     */
    public void set_PlayerNum(int player_num){
        this.player_num = player_num;
    }



    /**
     * Transformer
     * Preconditions: NONE
     * Postconditions:
     * Stops the currently playing music if any
     */
    public void Stop_music(){
        player_music.stop();
    }


    /**Observer
     * Initializes The Board
     * preconditions:
     * must be called before the game starts
     * Postconditions:
     * The board is created
     */
    public void initialize_board(){
         this.board = new Model.Board();

    }


    /**
     * Accessor
     * @return The game board instance
     * Preconditions: NONE
     * Postconditions: Returns the current board
     */
    public Board get_board(){
        return this.board;
    }

    /**Observer
     * Initializes players
     * preconditions:
     * player_num must be set before calling
     * Postconditions:
     * the number of Player Objects are created
     */
    public void initialize_players(){
        if(player_num==4) {
            player1 = new Player("Player1", "Yellow" ,"C:\\Project Java\\Project_252_B\\project_assets\\music\\Player1.wav" );
            initialize_cards(player1);
            player2 = new Player("Player2", "Blue" , "C:\\Project Java\\Project_252_B\\project_assets\\music\\Player2.wav");
            initialize_cards(player2);
            player3 = new Player("Player3", "Red" , "C:\\Project Java\\Project_252_B\\project_assets\\music\\Player3.wav");
            initialize_cards(player3);
            player4 = new Player("Player4", "Black", "C:\\Project Java\\Project_252_B\\project_assets\\music\\Player4.wav");
            initialize_cards(player4);
        }else if(player_num==1) {
            player1 = new Player("Player1", "Blue" ,"C:\\Project Java\\Project_252_B\\project_assets\\music\\Player1.wav");
            initialize_cards(player1);
            thief = new Player("Thief", "Black" ,"");
            initialize_cards(thief);
        }else{
            System.out.println("Wrong Number of players");

        }

    }



    /**
     * Transformer
     * @param num_of_players Number of players (1 or 4)
     * Preconditions:
     * Players must be initialized first
     * Postconditions:
     * The players list is populated with the correct players
     */
    public void set_players(int num_of_players){
        if(num_of_players==4){
            players.add(player1);
            players.add(player2);
            players.add(player3);
            players.add(player4);
        }else if(num_of_players==1){
            players.add(player1);
            players.add(thief);
        }
    }




    /**Observer
     * Initializes Cards
     * Preconditions:
     * players must be initialized
     * Postconditions:
     * Card Objects are initialized
     */
    public void initialize_cards(Player player){
        Archaeologist archeologist = new Archaeologist(player, "C:\\Project Java\\Project_252_B\\project_assets\\images\\archaeologist.png", " ", 0 , player.get_Color() , 2);
        Assistant assistant = new Assistant(player, "C:\\Project Java\\Project_252_B\\project_assets\\images\\assistant.png", " ", 0 , player.get_Color() , 1);
        Coder coder = new Coder(player, "C:\\Project Java\\Project_252_B\\project_assets\\images\\coder.PNG", " ", 0 , player.get_Color() ,2);
        Digger digger = new Digger(player, "C:\\Project Java\\Project_252_B\\project_assets\\images\\digger.png", " ", 0 , player.get_Color() , 2);
        Professor professor = new Professor(player, "C:\\Project Java\\Project_252_B\\project_assets\\images\\professor.png", " ", 0 , player.get_Color(), 3);

        player.AddCard(archeologist);
        player.AddCard(assistant);
        player.AddCard(coder);
        player.AddCard(digger);
        player.AddCard(professor);



    }

    /**Observer
     * Initializes Tiles
     * Preconditions:
     * Bag must be initialized
     * Postconditions:
     * All Tiles Objects are initialized
     */
    public void initialize_tiles(){
        for(int i=0; i<27; i++){
            if(i<9){
                MosaicTile mosaic = new MosaicTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\mosaic_green.png" , "Mosaic" , 0 , "Green");
                this.bag.addTile(mosaic);
            }else if(i>9 && i<18){
                MosaicTile mosaic = new MosaicTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\mosaic_red.png", "Mosaic" , 0 , "Red");
                this.bag.addTile(mosaic);
            }else{
                MosaicTile mosaic = new MosaicTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\mosaic_yellow.png", "Mosaic" , 0 , "Yellow");
                this.bag.addTile(mosaic);
            }
        }
        for(int i=0; i<24; i++){
            if(i<12){
                CaryatidTile caryatid = new CaryatidTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\caryatid.png", "statue" , 0);
                this.bag.addTile(caryatid);
            }else{
                ShpinxTile sphinx =  new ShpinxTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\sphinx.png", "statue" , 0);
                this.bag.addTile(sphinx);
            }
        }

        for(int i=0; i<24 ; i++){
            LandSlideTile landslide = new LandSlideTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\landslide.png", "Landslide" , 0);
            this.bag.addTile(landslide);
        }

        for(int i=0; i<30; i++){
            if(i<5){
                SkeletonTile small_skeleton = new SkeletonTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\skeleton_small_top.png" , "skeleton" , 0 , 0 , 0 );
                this.bag.addTile(small_skeleton);
            }else if(i>5 && i<10){
                SkeletonTile small_skeleton = new SkeletonTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\skeleton_small_bottom.png", "skeleton" , 0 , 0 , 1 );
                this.bag.addTile(small_skeleton);
            } else if(i>10 && i<20){
                SkeletonTile big_skeleton = new SkeletonTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\skeleton_big_top.png", "skeleton" , 0 , 1 , 0 );
                this.bag.addTile(big_skeleton);
            }else if(i>20){
                SkeletonTile big_skeleton = new SkeletonTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\skeleton_big_bottom.png", "skeleton" , 0 , 1 , 1 );
                this.bag.addTile(big_skeleton);
            }
        }

        for(int i=0; i<30; i++){
            if(i<5){
                AmphoraTile blue_amphora = new AmphoraTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\amphora_blue.png" ,  "amphora" , 0 , "Blue");
                this.bag.addTile(blue_amphora);
            }else if(i>5 && i<10){
                AmphoraTile brown_amphora = new AmphoraTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\amphora_brown.png", "amphora" , 0 , "Brown");
                this.bag.addTile(brown_amphora);
            } else if(i>10 && i<15){
                AmphoraTile red_amphora = new AmphoraTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\amphora_red.png", "amphora" , 0 , "Red");
                this.bag.addTile(red_amphora);
            } else if(i>15 && i<20){
                AmphoraTile green_amphora = new AmphoraTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\amphora_green.png", "amphora" , 0 , "Green");
                this.bag.addTile(green_amphora);
            }else if(i>20 && i<25){
                AmphoraTile yellow_amphora = new AmphoraTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\amphora_yellow.png", "amphora", 0 , "Yellow");
                this.bag.addTile(yellow_amphora);
            }else if(i>25){
                AmphoraTile purple_amphora = new AmphoraTile("C:\\Project Java\\Project_252_B\\project_assets\\images\\amphora_purple.png", "amphora", 0 , "Purple");
                this.bag.addTile(purple_amphora);
            }
        }


    }



    /**Observer
     * Initializes Tile Bag
     * Preconditions:
     * None
     * Postconditions:
     * The bag is initialized
     */
    public void initialize_bag(){
        this.bag = new Bag();
        initialize_tiles();
    }


    /**Observer
     *Gives Cards to each player
     * Preconditions:
     * players must be initialized
     * cards must be initialized
     * Postconditions:
     * Each player receives cards
     */
    public void share_cards(){

    }


    /**Observer
     * Shares one tile to each field
     * Preconditions:
     * Board must be initialized
     * Bag must contain Tiles
     * Postconditions:
     * Each field has a tile
     */
    public Tile share_tiles(){


        Random rand = new Random();
        Tile random_tile = bag.getTiles().get(rand.nextInt(bag.getTiles().size()));
        if(random_tile instanceof AmphoraTile){
            board.AddAmphoraTile((AmphoraTile) random_tile);
        }else if (random_tile instanceof SkeletonTile){
            board.AddSkeletonTile((SkeletonTile) random_tile);
        }else if (random_tile instanceof MosaicTile){
            board.AddMosaicTile((MosaicTile) random_tile);
        }else if (random_tile instanceof StatueTile){
            board.AddStatueTile((StatueTile) random_tile);
        }else if (random_tile instanceof LandSlideTile){
            board.add_Land_slide_tile((LandSlideTile) random_tile);
        }
        bag.removeTile(random_tile);
        return random_tile;

    }


    /**
     * Transformer
     * @param winner The winning player
     * Preconditions:
     * winner must not be null
     * Postconditions:
     * winner field is updated
     */
    public void set_winner(Player winner){
        this.winner = winner;
    }


    /**
     * Accessor
     * @return The winning player (or null if no winner yet)
     * Preconditions: NONE
     * Postconditions: Returns the current winner
     */
    public Player get_winner(){
        return winner;
    }


    /**
     * Transformer
     * @param player The new current player
     * Preconditions:
     * player must not be null
     * Postconditions:
     * currentPlayer is updated
     */
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }


    /**
     * Accessor
     * @return The current player
     * Preconditions: NONE
     * Postconditions: Returns the player whose turn it is
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }


    /**Observer
     * Sets the next player
     * Preconditions:
     * Player_to_start must be chosen
     * Postconditions:
     * next player plays
     */
    public Player next_player(){
        int index = players.indexOf(currentPlayer);
        player_music.stop();
        player_music.close();
        if(index>4){
            currentPlayer = players.get(0);
            currentPlayer.set_count(0);
        }else{
            int nextindex=(index+1)%players.size();
            currentPlayer = players.get(nextindex);
            currentPlayer.set_count(0);

        }
        try{
            File music = new File(currentPlayer.get_music());
            AudioInputStream current_music = AudioSystem.getAudioInputStream(music);
            player_music = AudioSystem.getClip();
            player_music.open(current_music);
            player_music.start();
            player_music.loop(Clip.LOOP_CONTINUOUSLY);

        }catch(Exception e){
            e.printStackTrace();
        }
        return currentPlayer;

    }

    /**Observer
     * Selects random a player to start a game
     * Preconditions:
     * players must be initialized
     * Postconditions:
     * The random chosen player starts
     */
    public void Player_to_start(){
        if(players.isEmpty()){
            return;
        }else{
            Random random_player = new Random();
            int random_player_index = random_player.nextInt(players.size());

            currentPlayer = players.get(random_player_index);
            try{
                File music = new File(currentPlayer.get_music());
                AudioInputStream current_music = AudioSystem.getAudioInputStream(music);
                player_music = AudioSystem.getClip();
                player_music.open(current_music);
                player_music.start();
                player_music.loop(Clip.LOOP_CONTINUOUSLY);

            }catch(Exception e){
                e.printStackTrace();
            }

        }


    }


    /**Observer
     * Starts The game
     * Preconditions:
     * bag,player,board and cards must be initialized
     * Postconditions:
     * Game starts
     */
    public void Start_game(){

    }

    /**Observer
     * Selects Tiles from the bag
     * Preconditions:
     * Bag must contain tiles
     * Postconditions:
     * selected tiles are removed from the bag
     */
    public void chose_tiles(String category){
        Random rand = new Random();
        Tile random_tile;

        do {
            random_tile = bag.getTiles().get(rand.nextInt(bag.getTiles().size()));
        } while (
                random_tile instanceof LandSlideTile ||
                        !random_tile.getCategory().equals(category)
        );

        if (random_tile instanceof MosaicTile) {
            board.AddMosaicTile((MosaicTile) random_tile);
        } else if (random_tile instanceof StatueTile) {
            board.AddStatueTile((StatueTile) random_tile);
        } else if (random_tile instanceof AmphoraTile) {
            board.AddAmphoraTile((AmphoraTile) random_tile);
        } else if (random_tile instanceof SkeletonTile) {
            board.AddSkeletonTile((SkeletonTile) random_tile);
        }

        bag.removeTile(random_tile);

    }


    /**Observer
     * A player chooses a tile to take from a field
     * Preconditions:
     * There must b a tile to the specific field
     * Postconditions:
     * The tile is removed from the field
     */
    public void remove_tiles(Tile tile){
        if(tile instanceof AmphoraTile){
            board.removeAmphoraTile((AmphoraTile)tile);
        }else if (tile instanceof SkeletonTile){
            board.removeSkeletonTile((SkeletonTile) tile);
        }else if (tile instanceof MosaicTile){
            board.removeMosaicTile((MosaicTile) tile);
        }else if (tile instanceof StatueTile){
            board.removeStatueTile((StatueTile) tile);
        }

    }


    /**Observer
     * A player uses card's ability
     * Preconditions:
     * Ability is unused
     * Postconditions:
     * Card Ability applies
     */
    public void use_ability(){

    }


    /**
     * Observer
     * Calculates additional points from statues (Sphinx and Caryatid)
     * Preconditions:
     * All players must have their tiles collected
     * Postconditions:
     * Statue bonus points are added to players
     */
    public void calculate_statue_points(){
        Player max_player= players.get(0);
        Player min_player=players.get(0);
        int minn_sphinx=players.get(0).get_sphinx_counter();
        int max_sphinx = 0;
        for(Player player : players){
            for(Tile tile : player.getPlayerTiles()){
                if(tile instanceof ShpinxTile){
                    player.set_sphinx_counter(player.get_sphinx_counter()+1);
                }
            }
        }
        for(Player player : players){
            if(player.get_sphinx_counter()>max_sphinx){
                max_player=player;
                max_sphinx = player.get_sphinx_counter();
            }
        }

        for(Player player : players){
            if(player.get_sphinx_counter()<minn_sphinx){
                min_player=player;
                minn_sphinx=player.get_sphinx_counter();
            }
        }

        if(max_player.get_sphinx_counter()>0){
            max_player.setPoints(max_player.getPoints()+6);
            System.out.println("MAX PLAYER:" + max_player.getPoints());
        }

        for(Player player : players){
            if(player!=max_player && player!=min_player){
                if(player.get_sphinx_counter()>0){
                    player.setPoints(player.getPoints()+3);
                    System.out.println("MID PLAYER:" + player.getPoints());
                }
            }
        }



        max_player= players.get(0);
        min_player=players.get(0);
        int minn_caryatid=players.get(0).get_caryarid_counter();
        int max_caryatid = 0;
        for(Player player : players){
            for(Tile tile : player.getPlayerTiles()){
                if(tile instanceof CaryatidTile){
                    player.set_caryarid_counter(player.get_caryarid_counter()+1);
                }
            }
        }
        for(Player player : players){
            if(player.get_caryarid_counter()>max_caryatid){
                max_player=player;
                max_caryatid = player.get_caryarid_counter();
            }
        }

        for(Player player : players){
            if(player.get_caryarid_counter()<minn_caryatid){
                min_player=player;
                minn_caryatid=player.get_caryarid_counter();
            }
        }

        if(max_player.get_caryarid_counter()>0){
            max_player.setPoints(max_player.getPoints()+6);
            System.out.println("MAX PLAYER CARYATID:" + max_player.getPoints());
        }

        for(Player player : players){
            if(player!=max_player && player!=min_player){
                if(player.get_caryarid_counter()>0){
                    player.setPoints(player.getPoints()+3);
                    System.out.println("MID PLAYER CARYATID:" + player.getPoints());
                }
            }
        }
    }


    /**Observer
     * Checks if there is a winner
     * Preconditions:
     * Game must be Started
     * Postconditions:
     * winner is set
     */
    public void is_winner(){

        int max=0;
        this.winner=null;

        for(Player player : players){
            player.SumPoints();
//            System.out.println("Player: "+player.getName());
//            System.out.println("Points: "+player.getPoints());
        }
        calculate_statue_points();
        for(int i=0;i<players.size();i++){
            if(players.get(i).getPoints()>max){
                max = players.get(i).getPoints();
                this.winner = players.get(i);
            }
        }

//        System.out.println("Winner: "+winner.getName());
//        System.out.println("Points: "+max);
    }

    /**Observer
     * Ends the game
     * Preconditions:
     * Game must be Started
     * Postconditions:
     * The game ends
     */
    public void end_game(){
        if(board.getLand_slide_field().size()==16) {
            System.exit(0);
        }

    }

    /**Observer
     * Saves the game
     * Preconditions:
     * Game must be Started
     * Postconditions:
     * Game is saved
     */
    public void save_game(){

    }


    /** Controller Constructor
     * Initializes Controller
     * Preconditions:
     * NONE
     * Postconditions:
     * Controller object is created
     */
    public Controller(){
        this.players = new ArrayList<>();
        set_PlayerNum(4);
        initialize_players();
        set_players(get_PlayerNum());
        initialize_bag();
        initialize_board();
        chose_tiles("Mosaic");
        chose_tiles("statue");
        chose_tiles("amphora");
        chose_tiles("skeleton");
        Player_to_start();
        next_player();
        end_game();

    }


}
