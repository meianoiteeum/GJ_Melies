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
public class Wall extends Actor{

    public Wall(int x, int y) {
        super(x, y);        
        initWall();
    }
    
    private void initWall() {        
        ImageIcon imageIcon = new ImageIcon("src/main/resources/wall.png");
        setImage(imageIcon);
    }
}
