package client;

import client.gui.GameSetup;

import javax.swing.*;

/**
 * Created by Daniel on 2016/11/02.
 */
public class Main {
    public static void main(String[] args) {
        GameSetup game = new GameSetup();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
