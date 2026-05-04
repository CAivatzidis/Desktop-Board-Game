package View;

import Controller.Controller;
import Model.Player;
import Model.Tile.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.util.List;


/**
 * Main GUI class. Displays board, player info, cards and tiles.
 */
public class View {
    private Controller controller;
    private JFrame window;
    private JPanel[] gridPanels;
    private JPanel Background_panel;
    private JPanel Tile_panel;
    private Timer turnTimer;





    /**
     * Converts Image to BufferedImage
     */
    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = bimage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return bimage;
    }




    /**
     * Starts 30-second turn timer
     */
    private void startTurnTimer(JLabel player_time, JButton end_turn) {
        if (turnTimer != null && turnTimer.isRunning()) {
            turnTimer.stop();
        }

        int timeLeft = 30;
        player_time.setText("Time left: " + timeLeft + "s");

        turnTimer = new Timer(1000, new ActionListener() {
            int counter = timeLeft;

            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                player_time.setText("Time left: " + counter + "s");

                if (counter <= 0) {
                    ((Timer)e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Time's up! Turn ends.", "Timeout", JOptionPane.INFORMATION_MESSAGE);
                    end_turn.doClick();
                }
            }
        });

        turnTimer.start();
    }



    /**
     * Repositions board grids on resize
     */
    private void repositionGrids() {
        int width = Background_panel.getWidth();
        int height = Background_panel.getHeight();

        int margin = 30;
        int gridWidth = (width - 15 * margin) / 2;
        int gridHeight = (height - 17 * margin) / 2;

        // Mosaic filed
        if (gridPanels[0] != null) {
            gridPanels[0].setBounds(margin - 5, margin - 9, gridWidth + 47, gridHeight + 42);
        }
        // Statue Filed
        if (gridPanels[1] != null) {
            gridPanels[1].setBounds(width - gridWidth - margin - 40, margin - 9, gridWidth + 46, gridHeight + 43);
        }
        // Amphora field
        if (gridPanels[2] != null) {
            gridPanels[2].setBounds(margin - 4, height - gridHeight - margin - 35, gridWidth + 45, gridHeight + 42);
        }
        // Skeleton field
        if (gridPanels[3] != null) {
            gridPanels[3].setBounds(width - gridWidth - margin - 40, height - gridHeight - margin -35, gridWidth + 45, gridHeight + 42);
        }
        //landSlide field
        if (gridPanels[4] != null) {
            gridPanels[4].setBounds(width /2 - 3*margin -35 ,  height /2 - margin -26, gridWidth +44, gridHeight +76);
        }
        Background_panel.revalidate();
        Background_panel.repaint();
    }


    /**
     * Updates player's collected tiles panel
     */
    public void update_tile_panel(){
            Tile_panel.removeAll();
            List<Tile> tiles_list=controller.getCurrentPlayer().getPlayerTiles();
            if(tiles_list==null){
                Tile_panel.revalidate();
                Tile_panel.repaint();

            }else{
                for(Tile tile : tiles_list){
                    ImageIcon image = new ImageIcon(tile.getImage());
                    Image correct_image = image.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
                    JLabel tile_label = new JLabel(new ImageIcon(correct_image));
                    tile_label.setPreferredSize(new Dimension(60, 60));
                    Tile_panel.add(tile_label);

                }
                Tile_panel.revalidate();
                Tile_panel.repaint();
            }
    }


    /**
     * Creates visual component for a tile
     */
    public void create_tile_button(Tile tile , JFrame window) {
        int grid;
        if (tile instanceof LandSlideTile) {
            JLabel label = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon background_image = new ImageIcon(tile.getImage());
                    g.drawImage(background_image.getImage(), 0, 0, getWidth(), getHeight(), this);

                }
            };
            label.setOpaque(false);
            gridPanels[4].add(label);
            Background_panel.add(gridPanels[4]);
            repositionGrids();

        }else {
            JButton btn = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon background_image = new ImageIcon(tile.getImage());
                    g.drawImage(background_image.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            if (tile instanceof MosaicTile) {
                grid = 0;
            } else if (tile instanceof StatueTile) {
                grid = 1;
            } else if (tile instanceof AmphoraTile) {
                grid = 2;
            } else if (tile instanceof SkeletonTile) {
                grid = 3;
            }else{
                grid = 0;
            }
            btn.setOpaque(false);
            gridPanels[grid].add(btn);
            Background_panel.add(gridPanels[grid]);
            repositionGrids();
            btn.addActionListener(e -> {
                if(controller.getCurrentPlayer().get_using_card()==1) {

                    //ARCHAEOLOGIST CASE
                    if (controller.getCurrentPlayer().getCards().get(0).get_counter() > 0 && controller.getCurrentPlayer().get_ability_used().equals("ARCHAEOLOGIST")) {
                        if(controller.getCurrentPlayer().getCards().get(0).get_counter() ==2){
                            controller.getCurrentPlayer().set_area(tile.getCategory());
                            controller.remove_tiles(tile);
                            controller.getCurrentPlayer().addPlayerTile(tile);
                            gridPanels[grid].remove(btn);
                            gridPanels[grid].revalidate();
                            update_tile_panel();
                            window.revalidate();
                            window.repaint();
                            controller.getCurrentPlayer().getCards().get(0).set_counter(controller.getCurrentPlayer().getCards().get(0).get_counter() - 1);
                        }else if(controller.getCurrentPlayer().getCards().get(0).get_counter()==1){
                            if (tile.getCategory().equals(controller.getCurrentPlayer().get_area())) {
                                controller.remove_tiles(tile);
                                controller.getCurrentPlayer().addPlayerTile(tile);
                                gridPanels[grid].remove(btn);
                                gridPanels[grid].revalidate();
                                update_tile_panel();
                                window.revalidate();
                                window.repaint();
                                controller.getCurrentPlayer().getCards().get(0).set_counter(controller.getCurrentPlayer().getCards().get(0).get_counter() - 1);
                            }
                        }

                    //ASSISTANT CASE
                    } else if (controller.getCurrentPlayer().getCards().get(1).get_counter() > 0 && controller.getCurrentPlayer().get_ability_used().equals("ASSISTANT")) {
                        controller.remove_tiles(tile);
                        controller.getCurrentPlayer().addPlayerTile(tile);
                        gridPanels[grid].remove(btn);
                        gridPanels[grid].revalidate();
                        update_tile_panel();
                        window.revalidate();
                        window.repaint();
                        controller.getCurrentPlayer().getCards().get(1).set_counter(controller.getCurrentPlayer().getCards().get(1).get_counter() - 1);

                    //DIGGER CASE
                    }else if(controller.getCurrentPlayer().getCards().get(3).get_counter() > 0 && controller.getCurrentPlayer().get_ability_used().equals("DIGGER")) {
                        if (tile.getCategory().equals(controller.getCurrentPlayer().get_area())) {
                            controller.remove_tiles(tile);
                            controller.getCurrentPlayer().addPlayerTile(tile);
                            gridPanels[grid].remove(btn);
                            gridPanels[grid].revalidate();
                            update_tile_panel();
                            window.revalidate();
                            window.repaint();
                            controller.getCurrentPlayer().getCards().get(3).set_counter(controller.getCurrentPlayer().getCards().get(3).get_counter() - 1);
                        }



                    //PROFESSOR CASE
                    }else if(controller.getCurrentPlayer().getCards().get(4).get_counter() > 0 && controller.getCurrentPlayer().get_ability_used().equals("PROFESSOR")) {
                        if (!tile.getCategory().equals(controller.getCurrentPlayer().get_area())) {
                            controller.remove_tiles(tile);
                            controller.getCurrentPlayer().addPlayerTile(tile);
                            gridPanels[grid].remove(btn);
                            gridPanels[grid].setVisible(false);
                            gridPanels[grid].revalidate();
                            update_tile_panel();
                            window.revalidate();
                            window.repaint();
                            controller.getCurrentPlayer().getCards().get(4).set_counter(controller.getCurrentPlayer().getCards().get(4).get_counter() - 1);
                        }
                    }



                }else {

                    if (controller.getCurrentPlayer().get_count() == 0) {
                        controller.getCurrentPlayer().set_area(tile.getCategory());
                        controller.remove_tiles(tile);
                        controller.getCurrentPlayer().addPlayerTile(tile);
                        gridPanels[grid].remove(btn);
                        gridPanels[grid].revalidate();
                        update_tile_panel();
                        window.revalidate();
                        window.repaint();
                        controller.getCurrentPlayer().set_count(controller.getCurrentPlayer().get_count() + 1);
                    } else if (controller.getCurrentPlayer().get_count() == 1){
                        if (tile.getCategory().equals(controller.getCurrentPlayer().get_area())) {
                            controller.remove_tiles(tile);
                            controller.getCurrentPlayer().addPlayerTile(tile);
                            gridPanels[grid].remove(btn);
                            gridPanels[grid].revalidate();
                            update_tile_panel();
                            window.revalidate();
                            window.repaint();
                            controller.getCurrentPlayer().set_count(controller.getCurrentPlayer().get_count() + 1);
                        }
                    }


                    //CODER CASE
                    if(!controller.getCurrentPlayer().getCards().get(2).getAbility().isEmpty() && controller.getCurrentPlayer().getCards().get(2).get_counter()>0){
                        if(tile.getCategory().equals(controller.getCurrentPlayer().getCards().get(2).getAbility())) {
                            controller.remove_tiles(tile);
                            controller.getCurrentPlayer().addPlayerTile(tile);
                            gridPanels[grid].remove(btn);
                            gridPanels[grid].revalidate();
                            update_tile_panel();
                            window.revalidate();
                            window.repaint();
                            controller.getCurrentPlayer().getCards().get(2).set_counter(controller.getCurrentPlayer().getCards().get(2).get_counter() - 1);
                        }
                    }

                }
            });
        }
    }



    /**
     * Constructor
     * creates controller and opens window
     */
    public View(){
        controller = new Controller();
        window_graphics(controller.getCurrentPlayer());

    }




    /**
     * Builds and shows main game window
     */
    public void window_graphics(Player player){

        //Main Window
        JFrame window = new JFrame("Amphipolis");
        window.setSize(1600 , 1200);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menu = new JMenuBar();
        JMenuItem New_game =new JMenuItem("New Game");
        New_game.addActionListener(e->{
            turnTimer.stop();
            controller.Stop_music();
            window.dispose();
            new View();
        });

        JMenuItem Save_game =new JMenuItem("Save Game");
        JMenuItem Exit_game =new JMenuItem("Exit Game");
        Exit_game.addActionListener(e->{
            System.exit(0);
        });

        window.setJMenuBar(menu);
        menu.add(New_game);
        menu.add(Save_game);
        menu.add(Exit_game);


        JPanel main_panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;


        //BACKGROUND PANEL RIGHT
        Background_panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background_image = new ImageIcon("C:\\Project Java\\Project_252_B\\project_assets\\images\\background.png");
                g.drawImage(background_image.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        Background_panel.setLayout(null);
        Background_panel.setOpaque(false);
        Background_panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repositionGrids();
            }
        });


        gridPanels = new JPanel[5];



        repositionGrids();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;
        main_panel.add(Background_panel, gbc);


        //PLAYER PANEL RIGHT

        JPanel Player_panel = new JPanel();
        Player_panel.setBackground(Color.lightGray);
        Player_panel.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 0.8;
        main_panel.add(Player_panel, gbc);


        GridBagConstraints pc = new GridBagConstraints();
        pc.fill = GridBagConstraints.BOTH;
        pc.gridx = 0;

        JPanel top_panel = new JPanel();
        top_panel.setBackground(Color.lightGray);
        top_panel.setLayout(new GridLayout(3,1));

        JLabel player_name = new JLabel(controller.getCurrentPlayer().getName());
        JLabel player_color = new JLabel("Use Character:");
        JLabel player_time = new JLabel();

        player_name.setHorizontalAlignment(SwingConstants.CENTER);
        player_name.setVerticalAlignment(SwingConstants.CENTER);

        player_color.setHorizontalAlignment(SwingConstants.CENTER);
        player_color.setVerticalAlignment(SwingConstants.CENTER);

        player_time.setHorizontalAlignment(SwingConstants.CENTER);
        player_time.setVerticalAlignment(SwingConstants.CENTER);

        top_panel.add(player_name);
        top_panel.add(player_time);
        top_panel.add(player_color);
        pc.gridy = 0;
        pc.weightx = 1;
        pc.weighty = 0.05;
        Player_panel.add(top_panel, pc);

        JPanel Card_panel = new JPanel();
        Card_panel.setBackground(Color.lightGray);
        Card_panel.setLayout(new GridLayout(2,3));




        JButton card1 = new JButton("Archeologist"){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Player currentPlayer = controller.getCurrentPlayer();
                if (currentPlayer != null) {
                    Image img = new  ImageIcon(currentPlayer.getCards().get(0).getImage()).getImage();
                    img = toBufferedImage(img);
                    if(currentPlayer.getCards().get(0).getIs_used()==1){
                        GrayFilter filtered_image = new GrayFilter(true, 40);
                        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filtered_image));
                    }
                    g.drawImage(img, 0, 0, getWidth() , getHeight() ,null);
                }
            }
        };
        card1.addActionListener(e-> {
            if (controller.getCurrentPlayer().get_using_card() != 1){
                if (controller.getCurrentPlayer().get_area().equals("Mosaic")) {
                    gridPanels[0].setVisible(false);
                } else if (controller.getCurrentPlayer().get_area().equals("statue")) {
                    gridPanels[1].setVisible(false);
                } else if (controller.getCurrentPlayer().get_area().equals("amphora")) {
                    gridPanels[2].setVisible(false);
                } else if (controller.getCurrentPlayer().get_area().equals("skeleton")) {
                    gridPanels[3].setVisible(false);
                }
                controller.getCurrentPlayer().set_using_card(1);
                controller.getCurrentPlayer().set_ability_used("ARCHAEOLOGIST");
                card1.setEnabled(false);
                controller.getCurrentPlayer().getCards().get(0).setIs_used(1);
                window.repaint();
                window.revalidate();

            }
        });

        JButton card2 = new JButton("Assistant"){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Player currentPlayer = controller.getCurrentPlayer();
                if (currentPlayer != null) {
                    Image img = new  ImageIcon(currentPlayer.getCards().get(1).getImage()).getImage();
                    img = toBufferedImage(img);
                    if(currentPlayer.getCards().get(1).getIs_used()==1){
                        GrayFilter filtered_image = new GrayFilter(true, 40);
                        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filtered_image));
                    }
                    g.drawImage(img, 0, 0, getWidth() , getHeight() ,null);
                }
            }
        };
        card2.addActionListener(e->{
            if(controller.getCurrentPlayer().get_using_card()!=1) {
                controller.getCurrentPlayer().set_using_card(1);
                controller.getCurrentPlayer().set_ability_used("ASSISTANT");
                card2.setEnabled(false);
                controller.getCurrentPlayer().getCards().get(1).setIs_used(1);
                window.repaint();
                window.revalidate();
            }
        });




        JButton card3 = new JButton("Coder"){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Player currentPlayer = controller.getCurrentPlayer();
                if (currentPlayer != null) {
                    Image img = new  ImageIcon(currentPlayer.getCards().get(2).getImage()).getImage();
                    img = toBufferedImage(img);
                    if(currentPlayer.getCards().get(2).getIs_used()==1){
                        GrayFilter filtered_image = new GrayFilter(true, 40);
                        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filtered_image));
                    }
                    g.drawImage(img, 0, 0, getWidth() , getHeight() ,null);
                }
            }
        };
        card3.addActionListener(e->{
            if(controller.getCurrentPlayer().get_using_card()!=1) {
                String[] options = {"amphora", "skeleton", "statue", "Mosaic"};
                String areaChoice = (String) JOptionPane.showInputDialog(window, "Choose an area", "Area Selection", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                System.out.println("Selected area: " + areaChoice);
                controller.getCurrentPlayer().getCards().get(2).setAbility(areaChoice);
                System.out.println(controller.getCurrentPlayer().getCards().get(2).getAbility());
                controller.getCurrentPlayer().set_using_card(1);
                controller.getCurrentPlayer().set_ability_used("CODER");
                controller.getCurrentPlayer().getCards().get(2).setIs_used(1);
                card3.setEnabled(false);
                window.repaint();
                window.revalidate();

            }
        });



        JButton card4 = new JButton("DIGGER"){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Player currentPlayer = controller.getCurrentPlayer();
                if (currentPlayer != null) {
                    Image img = new  ImageIcon(currentPlayer.getCards().get(3).getImage()).getImage();
                    img = toBufferedImage(img);
                    if(currentPlayer.getCards().get(3).getIs_used()==1){
                        GrayFilter filtered_image = new GrayFilter(true, 40);
                        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filtered_image));
                    }
                    g.drawImage(img, 0, 0, getWidth() , getHeight() ,null);
                }
            }

        };
        card4.addActionListener(e->{
            if(controller.getCurrentPlayer().get_using_card()!=1) {
                controller.getCurrentPlayer().set_using_card(1);
                controller.getCurrentPlayer().set_ability_used("DIGGER");
                controller.getCurrentPlayer().getCards().get(3).setIs_used(1);
                card4.setEnabled(false);
                window.repaint();
                window.revalidate();
            }
        });

        JButton card5 = new JButton("PROFESSOR"){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Player currentPlayer = controller.getCurrentPlayer();
                if (currentPlayer != null) {
                    Image img = new  ImageIcon(currentPlayer.getCards().get(4).getImage()).getImage();
                    img = toBufferedImage(img);
                    if(currentPlayer.getCards().get(4).getIs_used()==1){
                        GrayFilter filtered_image = new GrayFilter(true, 40);
                        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filtered_image));
                    }
                    g.drawImage(img, 0, 0, getWidth() , getHeight() ,null);
                }
            }

        };
        card5.addActionListener(e->{
            if(controller.getCurrentPlayer().get_using_card()!=1) {
                if (controller.getCurrentPlayer().get_area().equals("Mosaic")) {
                    gridPanels[0].setVisible(false);
                } else if (controller.getCurrentPlayer().get_area().equals("statue")) {
                    gridPanels[1].setVisible(false);
                } else if (controller.getCurrentPlayer().get_area().equals("amphora")) {
                    gridPanels[2].setVisible(false);
                } else if (controller.getCurrentPlayer().get_area().equals("skeleton")) {
                    gridPanels[3].setVisible(false);
                }
                controller.getCurrentPlayer().set_using_card(1);
                controller.getCurrentPlayer().set_ability_used("PROFESSOR");
                card4.setEnabled(false);
                controller.getCurrentPlayer().getCards().get(4).setIs_used(1);
                window.repaint();
                window.revalidate();

            }
        });
        Card_panel.add(card1);
        Card_panel.add(card2);
        Card_panel.add(card3);
        Card_panel.add(card4);
        Card_panel.add(card5);
        pc.gridy = 1;
        pc.weighty = 0.85;
        Player_panel.add(Card_panel, pc);


        JPanel button_panel = new JPanel();
        button_panel.setBackground(Color.lightGray);
        button_panel.setLayout(new GridLayout(2,1));



        gridPanels[0] = new JPanel(new GridLayout(4, 4, 1, 1));
        gridPanels[0].setOpaque(false);
        gridPanels[1] = new JPanel(new GridLayout(4, 4, 1, 1));
        gridPanels[1].setOpaque(false);
        gridPanels[2] = new JPanel(new GridLayout(4, 4, 1, 1));
        gridPanels[2].setOpaque(false);
        gridPanels[3] = new JPanel(new GridLayout(4, 4, 1, 1));
        gridPanels[3].setOpaque(false);
        gridPanels[4] = new JPanel(new GridLayout(4, 4, 1, 1));
        gridPanels[4].setOpaque(false);
        for (Tile tile : controller.get_board().getMosaic_field()) {
            create_tile_button(tile, window);
        }
        for (Tile tile : controller.get_board().getStatue_field()) {
            create_tile_button(tile, window);
        }
        for (Tile tile : controller.get_board().getAmphora_field()) {
            create_tile_button(tile, window);
        }
        for (Tile tile : controller.get_board().getSkeleton_field()) {
            create_tile_button(tile, window);
        }

        repositionGrids();

        JButton draw_tiles = new JButton("Draw Tiles");
        draw_tiles.addActionListener(e -> {
            draw_tiles.setEnabled(false);
            for(int i = 0; i < 4; i++) {
                Tile random_tile = controller.share_tiles();
                create_tile_button(random_tile , window);
                if(gridPanels[4].getComponentCount() == 16) {
                    JOptionPane.showMessageDialog(null, "Gate is closed because of the landslide.\n"+"GAME IS OVER!", "Error", JOptionPane.ERROR_MESSAGE);
                    controller.is_winner();
                    if(controller.get_winner()!=null){
                        JOptionPane.showMessageDialog(null, "Winner is: "+ controller.get_winner().getName()+"\n" + "With "+ controller.get_winner().getPoints() +" points", "Winner", JOptionPane.INFORMATION_MESSAGE);

                    }else{
                        JOptionPane.showMessageDialog(null, "There is no winner!", " None winner", JOptionPane.ERROR_MESSAGE);
                    }

                    controller.end_game();
                }
            }

        });



        JButton end_turn = new JButton("End Turn");
        startTurnTimer(player_time, end_turn);
        end_turn.addActionListener(e -> {
            controller.getCurrentPlayer().set_ability_used("");
            controller.getCurrentPlayer().set_using_card(0);
            controller.getCurrentPlayer().set_area("");
            Player next_player = controller.next_player();

            if(next_player!=null){

                player_name.setText(next_player.getName());
                System.out.println(next_player.getName());
                card1.setEnabled(true);
                card2.setEnabled(true);
                card3.setEnabled(true);
                card4.setEnabled(true);
                gridPanels[0].setVisible(true);
                gridPanels[1].setVisible(true);
                gridPanels[2].setVisible(true);
                gridPanels[3].setVisible(true);
                if(next_player.getCards().get(0).getIs_used()==1){
                    card1.setEnabled(false);
                }
                card1.repaint();

                if(next_player.getCards().get(1).getIs_used()==1){
                    card2.setEnabled(false);
                }
                card2.repaint();

                if(next_player.getCards().get(2).getIs_used()==1){
                    card3.setEnabled(false);
                }
                card3.repaint();

                if(next_player.getCards().get(3).getIs_used()==1){
                    card4.setEnabled(false);
                }
                card4.repaint();

                if(next_player.getCards().get(4).getIs_used()==1){
                    card5.setEnabled(false);
                }
                card5.repaint();
                update_tile_panel();
                window.revalidate();
                window.repaint();
                draw_tiles.setEnabled(true);
                startTurnTimer(player_time, end_turn);
            }
        });
        pc.gridy = 2;
        pc.weighty = 0.10;
        Player_panel.add(button_panel, pc);
        button_panel.add(draw_tiles);
        button_panel.add(end_turn);



        Tile_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5 , 5));
        Tile_panel.setBackground(Color.lightGray);
        Tile_panel.setPreferredSize(new Dimension(1, 130));
        Tile_panel.setMinimumSize(new Dimension(1, 80));
        Tile_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        main_panel.add(Tile_panel, gbc);


        main_panel.setPreferredSize(new Dimension(1400, 1000));
        window.setContentPane(main_panel);
        window.pack();
        window.setVisible(true);


    }
}
