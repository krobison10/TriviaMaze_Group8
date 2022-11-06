package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BuildGUI {

    public static void main(String theArgs[]) {

        final int windowSizeWidth = 500;
        final int windowSizeHeight = 500;

        // create JFrame
        JFrame mazeWindow = new JFrame();
        mazeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mazeWindow.setResizable(false);
        mazeWindow.setTitle("Trivia Maze");
        mazeWindow.setSize(windowSizeWidth,windowSizeHeight);
        mazeWindow.setLocationRelativeTo(null);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        // Create menu
        JMenu startMenu = new JMenu("Start");
        JMenu helpMenu = new JMenu("Help");
        // Create menu items
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem saveGame = new JMenuItem("Save Game");
        JMenuItem exitGame = new JMenuItem("Exit Game");
        JMenuItem howToPlay = new JMenuItem("Rules & Controls");
        JMenuItem credits = new JMenuItem("Credits");
        // Create buttons
        JButton playButton = new JButton("Play");
        // Add menu to menubar
        menuBar.add(startMenu);
        menuBar.add(helpMenu);
        // Add menu items to start menu
        startMenu.add(newGame);
        startMenu.add(saveGame);
        startMenu.add(exitGame);
        // Add menu items to help menu
        helpMenu.add(howToPlay);
        helpMenu.add(credits);

        // Add everything to frame
        mazeWindow.getContentPane().add(BorderLayout.NORTH, menuBar);
        mazeWindow.getContentPane().add(playButton);

        mazeWindow.setVisible(true);

    }
}
