/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.melies;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Midni
 */
public class Player extends Actor{
    private static final String WALK = "src/main/resources/player_walk.gif";
    private static final String STOPPED = "src/main/resources/player_stopped.png";
    private static final String PUSH = "src/main/resources/player_push.gif";
    
    public Player(int x, int y) {
        super(x, y);
        stop(Orientation.UP);
    }

    public void move(int x, int y) {
        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
    
    public void walk(Orientation orientation){
        setOrientation(orientation);
        loadImage(WALK);
    }
    
    public void push(Orientation orientation) {
        setOrientation(orientation);
        loadImage(PUSH);
    }
    
    public void stop(Orientation orientation){
        setOrientation(orientation);
        loadImage(STOPPED);
    }
    
    private void loadImage(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        setImage(imageIcon);
    }
}
