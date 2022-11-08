package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildGUI implements ActionListener {

    private JFrame mazeWindow;
    private JMenuBar menuBar;
    private JMenu startMenu, helpMenu;
    private JMenuItem newGame, saveGame, exitGame, rules, controls, credits;
    private JButton playButton;

    public void buildFrame() {

        final int windowSizeWidth = 500;
        final int windowSizeHeight = 500;

        // create JFrame
        mazeWindow = new JFrame();
        mazeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mazeWindow.setResizable(false);
        mazeWindow.setTitle("Trivia Maze");
        mazeWindow.setSize(windowSizeWidth,windowSizeHeight);
        mazeWindow.setLocationRelativeTo(null);

        // Create menu bar
        menuBar = new JMenuBar();
        // Create menu
        startMenu = new JMenu("Start");
        helpMenu = new JMenu("Help");
        // Create menu items
        newGame = new JMenuItem("New");
        saveGame = new JMenuItem("Save");
        exitGame = new JMenuItem("Exit");
        rules = new JMenuItem("Rules");
        controls = new JMenuItem("Controls");
        credits = new JMenuItem("Credits");
        // Create buttons
        playButton = new JButton("Play");

        // Add menu to menubar
        menuBar.add(startMenu);
        menuBar.add(helpMenu);
        // Add menu items to start menu
        startMenu.add(newGame);
        startMenu.add(saveGame);
        startMenu.add(exitGame);
        // Add menu items to help menu
        helpMenu.add(rules);
        helpMenu.add(controls);
        helpMenu.add(credits);

        // Add everything to frame
        mazeWindow.getContentPane().add(BorderLayout.NORTH, menuBar);
        mazeWindow.getContentPane().add(playButton);

        // Add action listeners
        newGame.addActionListener(this);
        saveGame.addActionListener(this);
        exitGame.addActionListener(this);
        rules.addActionListener(this);
        controls.addActionListener(this);
        credits.addActionListener(this);

        // show frame
        mazeWindow.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            JOptionPane.showMessageDialog(mazeWindow,"Operation not implemented");
        } else if (e.getSource() == saveGame) {
            JOptionPane.showMessageDialog(mazeWindow,"Operation not implemented");
        } else if (e.getSource() == exitGame) {
            System.exit(0);
        } else if (e.getSource() == rules) {
            JOptionPane.showMessageDialog(mazeWindow,gameRules());
        } else if (e.getSource() == controls) {
            JOptionPane.showMessageDialog(mazeWindow,gameControls());
        } else if (e.getSource() == credits) {
            JOptionPane.showMessageDialog(mazeWindow, gameCredits());
        }
    }

    private String gameRules() {
        return "This is a 5x5 grid maze. \n" +
                "To enter another room, interact with a locked door. \n" +
                "Once a door is interacted with, a trivia question will display. \n" +
                "Answering the question correctly will unlock the door. \n" +
                "Answering the question incorrectly will lock it permanently. \n" +
                "You must reach the final room to complete the maze. \n" +
                "The game ends when the final room is reach or there is no path to th exit, \n" +
                "due to permanently locked doors";
    }

    private String gameCredits() {
        return "Created by Kyler Robison, AJ Garcia, and Minh Le.";
    }

    private String gameControls() {
        return "Use arrows or WASD to move left, right up, or down. \n" +
                "Press 'E' to interact with the environment.";
    }
}
