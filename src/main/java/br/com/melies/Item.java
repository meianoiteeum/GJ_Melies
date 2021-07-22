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
public class Item extends Actor{
    
    public Item(int x, int y, String path) {
        super(x, y);        
        initItenm(path);
    }
    
    private void initItenm(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        setImage(imageIcon);
    }

    public void move(int x, int y) {        
        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
}
