/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.melies;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Midni
 */
public class Main extends JFrame{
    private final int OFFSET = 30;

    public Main() {
        initUI();
    }

    private void initUI() {        
        Board board = new Board();
        add(board);

        setTitle("Game");
        
        setSize(board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2 * OFFSET);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {        
        EventQueue.invokeLater(() -> {            
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
