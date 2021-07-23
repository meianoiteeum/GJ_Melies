/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.melies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Midni
 */
public class Board extends JPanel {    
    private static final int WIDTH_SCREEN = 250;
    private static final int HEIGHT_SCREEN = 250;
    private static final int OFFSET = 30;
    private static final int SPACE = 20;
    private static final int LEFT_COLLISION = 1;
    private static final int RIGHT_COLLISION = 2;
    private static final int TOP_COLLISION = 3;
    private static final int BOTTOM_COLLISION = 4;
    private static final String DORFLEX = "src/main/resources/remedio_dorflex.png";
    private static final String MERTHIOLATE = "src/main/resources/remedio_merthiolate.png";
    private static final String MULTIGRIPE = "src/main/resources/remedio_multigripe.png";
    private static final String SUCO = "src/main/resources/remedio_suco.png";
    
    private List<Wall> walls;
    private List<Item> itens;
    private List<AreaGame> areas;
    
    private Player player;
    private int w = 0;
    private int h = 0;
    
    private boolean isCompleted = false;

    private static final String level
            =   "#################################################################\n" +
                "#.     #                    #        #                         .#\n" +
                "#      #  ############      #        #  ###################     #\n" +
                "#      #        #     #######        #           #        #     #\n" +
                "#####  #  ####  #           #         # #  ####  #              #\n" +
                " #     #  #  #  #  #######  #         # #  #     ################\n" +
                " #     #  #  #     #        #         #    #                    #\n" +
                " #  ####  #  #  ####  #### ####################                 #\n" +
                " #  #     ####     #        #                 ###############   #\n" +
                " #  # ## #   #############  #                  #      #         #\n" +
                " #  #        #                              ####      #  ####   #\n" +
                " #  #######  #                                     #  #  #      #\n" +
                " #           #          D              G    ####   #  #  #   #  #\n" +
                "##   ##############     M         @    S       #   #     #   #  #\n" +
                "#  #######   #  #                              #####     ########\n" +
                "#  #         #  #    #                         #   #######\n" +
                "#  #  #####  #  ###  #                         #\n" +
                "#  #  #      #       #           #  #          #          ####\n" +
                "#  #  #      #       #######  ####  #######################  #\n" +
                "#  #  #  #############     #  ####    #        #   #         #\n" +
                "#     #                    #  #   #   ######   #   #  #####  #\n" +
                "#     #  ############   ## #  #       #        #   #  #   #  #\n" +
                "#######             #   ## #  #     ###  ####  #   #  #   #  #\n" +
                "#     ################  ## #  #  ###  #  #  #  #   #  #   #  #\n" +
                "#                       ## #  #       #  #  #  #####  #####  #\n" +
                "####  ###################  #  ######  #  #  #         #      ######\n" +
                "#. #                       #  #       #  ###########  # #### ##   #\n" +
                "#  ############# #####  #  #  # ##### #            ####           #\n" +
                "#              #           #  #     # #  ###### #  #   #########  #\n" +
                "#  ##########  #  ####  #  #  ##### # #  ###### #  #              #\n" +
                "####        #  #  #     #  #  #     # #         #  #  #############\n" +
                "            #     #  #  #  #  # ##### ###########  #  #           #\n" +
                "            #     #  #  #  #  #                    #  #           #\n" +
                "            ####################################   #             .#\n" +
                "                                               ####################\n";

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener((KeyListener) new TAdapter());
        setFocusable(true);
        if(apresentation())
            initWorld();
    }
    
    private boolean apresentation(){
        String message = "Game Final de Fundamentos de Programação\n"
                        + "Ajude o Alberto a conseguir seus remédio para o coração que estão espalhados pelo cenário.\n"
                        + "Deixe cada remédio em um baú para ajudá-lo.\n"
                        + "Utilize as setas do teclado para andar (↑↓←→) e a tecla R para restart.\n";
        JOptionPane.showMessageDialog(this, message, "Game Jam - 2021", 2);
        return true;
    }
    
    private void initWorld() {        
        this.walls = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.areas = new ArrayList<>();

        int x = OFFSET;
        int y = OFFSET;

        Wall wall;
        Item item;
        AreaGame areaGame;

        for (int i = 0; i < level.length(); i++) {
            char character = level.charAt(i);
            switch (character) {
                case '\n':
                    y += SPACE;
                    if (this.w < x) {
                        this.w = x;
                    }
                    x = OFFSET;
                    break;

                case '#':
                    wall = new Wall(x, y);
                    walls.add(wall);
                    x += SPACE;
                    break;

                case 'D':
                    item = new Item(x, y, DORFLEX);
                    itens.add(item);
                    x += SPACE;
                    break;
                case 'M':
                    item = new Item(x, y, MERTHIOLATE);
                    itens.add(item);
                    x += SPACE;
                    break;
                case 'G':
                    item = new Item(x, y, MULTIGRIPE);
                    itens.add(item);
                    x += SPACE;
                    break;
                case 'S':
                    item = new Item(x, y, SUCO);
                    itens.add(item);
                    x += SPACE;
                    break;
                case '.':
                    areaGame = new AreaGame(x, y);
                    areas.add(areaGame);
                    x += SPACE;
                    break;
                case '@':
                    player = new Player(x, y);
                    x += SPACE;
                    break;
                case ' ':
                    x += SPACE;
                    break;

                default:
                    break;
            }
            h = y;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        buildWorld(g);
    }
    
    private void buildWorld(Graphics g) {
        g.setColor(new Color(162,42,230));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        List<Actor> world = new ArrayList<>();

        world.addAll(walls);
        world.addAll(areas);
        world.addAll(itens);
        world.add(player);

        for (int i = 0; i < world.size(); i++) {
            Actor actor = world.get(i);
            if (actor instanceof Player){
                transformImage(g, actor.getOrientation(), actor.getImage(), actor.x() + 10, actor.y() + 10);
            }else if( actor instanceof Item) 
                g.drawImage(actor.getImage().getImage(), actor.x() + 2, actor.y() + 2, this);
            else
                g.drawImage(actor.getImage().getImage(), actor.x(), actor.y(), this);

        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (isCompleted && key != KeyEvent.VK_R)
                return;

            switch (key) {                
                case KeyEvent.VK_LEFT:                    
                    if (checkWallCollision(player,LEFT_COLLISION))
                        return;
                    
                    if (checkBagCollision(LEFT_COLLISION))
                        return;
                    
                    player.move(-SPACE, 0);
                    player.walk(Orientation.LEFT);
                    break;
                    
                case KeyEvent.VK_RIGHT:                    
                    if (checkWallCollision(player, RIGHT_COLLISION))
                        return;
                    
                    if (checkBagCollision(RIGHT_COLLISION))
                        return;
                    
                    player.move(SPACE, 0);
                    player.walk(Orientation.RIGHT);
                    break;
                    
                case KeyEvent.VK_UP:                    
                    if (checkWallCollision(player, TOP_COLLISION))
                        return;
                    
                    if (checkBagCollision(TOP_COLLISION))
                        return;
                    
                    player.move(0, -SPACE);
                    player.walk(Orientation.UP);
                    break;
                    
                case KeyEvent.VK_DOWN:                    
                    if (checkWallCollision(player, BOTTOM_COLLISION))
                        return;
                    
                    if (checkBagCollision(BOTTOM_COLLISION))
                        return;
                    
                    player.move(0, SPACE);
                    player.walk(Orientation.DOWN);
                    break;
                    
                case KeyEvent.VK_R:                    
                    restartLevel();                    
                    break;
                    
                default:
                    break;
            }

            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {                
                case KeyEvent.VK_LEFT:
                    player.stop(Orientation.LEFT);
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    player.stop(Orientation.RIGHT);
                    break;
                    
                case KeyEvent.VK_UP:
                    player.stop(Orientation.UP);
                    break;
                    
                case KeyEvent.VK_DOWN:
                    player.stop(Orientation.DOWN);
                    break;
                
                default:
                    break;
            }
        }
    }

    private boolean checkWallCollision(Actor actor, int type) {
        switch (type) {            
            case LEFT_COLLISION:                
                for (int i = 0; i < walls.size(); i++) {                    
                    Wall wall = walls.get(i);                    
                    if (actor.isLeftCollision(wall))
                        return true;
                }                
                return false;
                
            case RIGHT_COLLISION:                
                for (int i = 0; i < walls.size(); i++) {                    
                    Wall wall = walls.get(i);                    
                    if (actor.isRightCollision(wall))
                        return true;
                }                
                return false;
                
            case TOP_COLLISION:                
                for (int i = 0; i < walls.size(); i++) {                    
                    Wall wall = walls.get(i);                    
                    if (actor.isTopCollision(wall))
                        return true;
                }                
                return false;
                
            case BOTTOM_COLLISION:                
                for (int i = 0; i < walls.size(); i++) {                    
                    Wall wall = walls.get(i);                    
                    if (actor.isBottomCollision(wall))
                        return true;
                }                
                return false;
                
            default: break;
        }        
        return false;
    }

    private boolean checkBagCollision(int type) {
        switch (type) {            
            case LEFT_COLLISION:                
                for (int i = 0; i < itens.size(); i++) {
                    Item bag = itens.get(i);
                    if (player.isLeftCollision(bag)) {
                        for (int j = 0; j < itens.size(); j++) {                            
                            Item item = itens.get(j);                            
                            if (!bag.equals(item))
                                if (bag.isLeftCollision(item))
                                    return true;                
                            
                            if (checkWallCollision(bag, LEFT_COLLISION))
                                return true;
                        }                        
                        bag.move(-SPACE, 0);
                        isCompleted();
                    }
                }                
                return false;
                
            case RIGHT_COLLISION:                
                for (int i = 0; i < itens.size(); i++) {
                    Item bag = itens.get(i);                    
                    if (player.isRightCollision(bag)) {                        
                        for (int j = 0; j < itens.size(); j++) {
                            Item item = itens.get(j);                            
                            if (!bag.equals(item))
                                if (bag.isRightCollision(item))
                                    return true;
                            
                            if (checkWallCollision(bag, RIGHT_COLLISION))
                                return true;
                        }                        
                        bag.move(SPACE, 0);
                        isCompleted();
                    }
                }
                return false;
                
            case TOP_COLLISION:                
                for (int i = 0; i < itens.size(); i++) {
                    Item bag = itens.get(i);                    
                    if (player.isTopCollision(bag)) {                        
                        for (int j = 0; j < itens.size(); j++) {
                            Item item = itens.get(j);
                            if (!bag.equals(item))
                                if (bag.isTopCollision(item))
                                    return true;
                            
                            if (checkWallCollision(bag, TOP_COLLISION))
                                return true;
                        }                        
                        bag.move(0, -SPACE);
                        isCompleted();
                    }
                }
                return false;
                
            case BOTTOM_COLLISION:                
                for (int i = 0; i < itens.size(); i++) {
                    Item bag = itens.get(i);                    
                    if (player.isBottomCollision(bag)) {                        
                        for (int j = 0; j < itens.size(); j++) {
                            Item item = itens.get(j);                            
                            if (!bag.equals(item))
                                if (bag.isBottomCollision(item))
                                    return true;
                            
                            if (checkWallCollision(bag,BOTTOM_COLLISION))
                                return true;
                        }                        
                        bag.move(0, SPACE);
                        isCompleted();
                    }
                }                
                break;
                
            default: break;
        }
        return false;
    }

    public void isCompleted() {
        int nOfBags = itens.size();
        int finishedBags = 0;

        for (int i = 0; i < nOfBags; i++) {            
            Item item = itens.get(i);            
            for (int j = 0; j < nOfBags; j++) {                
                AreaGame area =  areas.get(j);                
                if (item.x() == area.x() && item.y() == area.y())
                    finishedBags += 1;
            }
        }

        if (finishedBags == nOfBags) {            
            isCompleted = true;
            JOptionPane.showMessageDialog(this, "Você salvou o Alberto", "Finish", 2);
            repaint();
        }
    }

    public void restartLevel() {
        areas.clear();
        itens.clear();
        walls.clear();

        initWorld();

        if (isCompleted)
            isCompleted = false;
    }
    
    private void transformImage(Graphics g, Orientation orientation, ImageIcon image, int x, int y){
        double giro = 0;
        Graphics2D g2d = (Graphics2D) g;
        
        switch(orientation){
            case LEFT:
                giro = Math.PI * 270/180;
                break;
            case RIGHT:
                giro = Math.PI * 90/180;
                break;
            case UP:                
                giro = Math.PI * 0/180;
                break;
            case DOWN:
                giro = Math.PI * 180/180;
                break;
        }
        g2d.translate(x, y);
        g2d.rotate(giro);

        image.paintIcon(this, g, -(image.getIconWidth()/ 2), -(image.getIconHeight()/2));
    }    
    
    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }
}
