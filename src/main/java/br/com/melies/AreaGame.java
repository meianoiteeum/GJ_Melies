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
public class AreaGame extends Actor{
    
    public AreaGame(int x, int y) {
        super(x, y);        
        initArea();
    }
    
    private void initArea() {
        ImageIcon imageIcon = new ImageIcon("src/main/resources/bau.png");
        setImage(imageIcon);
    }
}
