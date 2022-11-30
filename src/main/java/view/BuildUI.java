/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.TriviaMazeController;

/**
 *
 *
 * @author AJ Garcia
 */
public class BuildUI implements ActionListener {
    /**
     * Singleton instance.
     */
    private static BuildUI instance;
    /**
     *
     */
    private JFrame myMazeWindow;
    /**
     *
     */
    private JMenuItem myNewGame, mySaveGame, myExitGame, myRules, myControls, myCredits;
//    private JButton playButton;


    private BuildUI() {}

    /**
     * Returns a reference to the singleton instance.
     */
    public static BuildUI getInstance() {
        if(instance == null) {
            instance = new BuildUI();
        }
        return instance;
    }

    /**
     *
     */
    public void buildFrame() {

        // initial welcome screen size
        final int windowSizeWidth = 300;
        final int windowSizeHeight = 300;

        // create JFrame
        myMazeWindow = new JFrame();
        myMazeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMazeWindow.setResizable(false);
        myMazeWindow.setTitle("Trivia Maze");
        myMazeWindow.setSize(windowSizeWidth,windowSizeHeight);
        myMazeWindow.setLocationRelativeTo(null);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        // Create menu
        JMenu startMenu = new JMenu("Start");
        JMenu helpMenu = new JMenu("Help");
        // Create menu items
        myNewGame = new JMenuItem("New");
        mySaveGame = new JMenuItem("Save");
        myExitGame = new JMenuItem("Exit");
        myRules = new JMenuItem("Rules");
        myControls = new JMenuItem("Controls");
        myCredits = new JMenuItem("Credits");
        // Create buttons
//        playButton = new JButton("Play");

        // Add menu to menubar
        menuBar.add(startMenu);
        menuBar.add(helpMenu);
        // Add menu items to start menu
        startMenu.add(myNewGame);
        startMenu.add(mySaveGame);
        startMenu.add(myExitGame);
        // Add menu items to help menu
        helpMenu.add(myRules);
        helpMenu.add(myControls);
        helpMenu.add(myCredits);


        // Add everything to frame
        myMazeWindow.getContentPane().add(BorderLayout.NORTH, menuBar);
//        mazeWindow.getContentPane().add(playButton);

        // Add action listeners
        myNewGame.addActionListener(this);
        mySaveGame.addActionListener(this);
        myExitGame.addActionListener(this);
        myRules.addActionListener(this);
        myControls.addActionListener(this);
        myCredits.addActionListener(this);

        // show frame
        myMazeWindow.setVisible(true);

    }

    /**
     * Gives actions to the menu bar items.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myNewGame) {
            myNewGame.setEnabled(false);
            buildMazeUI();
        } else if (e.getSource() == mySaveGame) {
            JOptionPane.showMessageDialog(myMazeWindow,"Operation not implemented");
        } else if (e.getSource() == myExitGame) {
            System.exit(0);
        } else if (e.getSource() == myRules) {
            JOptionPane.showMessageDialog(myMazeWindow,gameRules());
        } else if (e.getSource() == myControls) {
            JOptionPane.showMessageDialog(myMazeWindow,gameControls());
        } else if (e.getSource() == myCredits) {
            JOptionPane.showMessageDialog(myMazeWindow, gameCredits());
        }
    }

    /**
     * Builds the Trivia Maze UI
     */
    private void buildMazeUI() {
        //For some reason the default layout manager on splitpane blocks printlns,
        //if you set its layout manager to null it breaks the layout but printlns work again
        JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainPane.setBorder(null);
        mainPane.setDividerSize(0);

        TMPanel maze = TMPanel.getTriviaMaze();
        JPanel ui = SidebarManager.getInstance().getPanel();

        mainPane.setLeftComponent(maze);
        mainPane.setRightComponent(ui);

        myMazeWindow.add(mainPane);

        myMazeWindow.pack();

        myMazeWindow.setLocationRelativeTo(null);
        myMazeWindow.setVisible(true);
        myMazeWindow.add(new JButton("Click me!"));
        TriviaMazeController.getInstance().startNewGame();
        SidebarManager.getInstance().updateForCurrentRoom();
    }

    /**
     * Displays the game rules when selected from the menu bar
     * @return
     */
    private String gameRules() {

        return """
                This is a 5x5 grid maze.
                To enter another room, interact with a locked door.
                Once a door is interacted with, a trivia question will display.
                Answering the question correctly will unlock the door.
                Answering the question incorrectly will lock it permanently.
                You must reach the final room to complete the maze.
                The game ends when the final room is reached or there is no path to the exit,
                due to permanently locked doors";
                """;
    }

    /**
     * Displays the game credits when selected from the menu bar.
     * @return
     */
    private String gameCredits() {
        return "Created by Kyler Robison, AJ Garcia, and Minh Le.";
    }

    /**
     * Shows the game controls when selected from the menu bar.
     * @return
     */
    private String gameControls() {
        return """
                Use arrows or WASD to move left, right up, or down.
                Press 'E' to interact with the environment.
                """;
    }
}
