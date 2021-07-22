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
public class Actor {
    private final int SPACE = 20;
    private int x;
    private int y;
    private ImageIcon image;
    private Orientation orientation;

    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int x() {        
        return x;
    }

    public int y() {        
        return y;
    }

    public void setX(int x) {        
        this.x = x;
    }

    public void setY(int y) {        
        this.y = y;
    }

    public Orientation getOrientation() {
        return orientation;
    }
    
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public boolean isLeftCollision(Actor actor) {        
        return x() - SPACE == actor.x() && y() == actor.y();
    }

    public boolean isRightCollision(Actor actor) {        
        return x() + SPACE == actor.x() && y() == actor.y();
    }

    public boolean isTopCollision(Actor actor) {        
        return y() - SPACE == actor.y() && x() == actor.x();
    }

    public boolean isBottomCollision(Actor actor) {        
        return y() + SPACE == actor.y() && x() == actor.x();
    }
    
}
