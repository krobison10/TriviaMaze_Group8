/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import java.awt.*;

import javax.swing.*;

import model.mazeElements.*;

/**
 * Class that sets up and manages the sidebar.
 *
 * @author Kyler Robison
 */
public class SidebarManager {

    /**
     * Constant for the width of the sidebar panel.
     */
    public static final int WIDTH = 200;

    /**
     * Singleton instance of the class.
     */
    private static SidebarManager instance;
    /**
     * Panel of the sidebar.
     */
    private JPanel myPanel;


    private SidebarManager() {}

    /**
     * @return a reference to the singleton instance of the class.
     */
    public static SidebarManager getInstance() {
        if(instance == null) {
            instance = new SidebarManager();
        }
        return instance;
    }

    /**
     * Wipes and updates the sidebar to have the interactivity required for the current room.
     */
    public void updateForCurrentRoom() {
        myPanel.removeAll();

        //Stores the directions of the doors
        String[] directions = {"West", "North", "East", "South"};

        Room r = Player.getInstance().getCurrentRoom();
        if(r == null) {
            myPanel.revalidate();
            myPanel.repaint();
            return;
        }


        //Add first spacer
        JLabel spacer = new JLabel(" ");
        spacer.setPreferredSize(new Dimension(WIDTH, 50));
        myPanel.add(spacer);

        int i = 0;
        for(Door d : r.getDoors()) {
            if(d != null && d.getState() == DoorStates.CLOSED) {

                //Create and add label
                JLabel label = new JLabel(directions[i] + " door");
                label.setPreferredSize(new Dimension(WIDTH, 25));
                label.setHorizontalAlignment(SwingConstants.CENTER);

                myPanel.add(label);


                //Create and add button
                JButton btn = new JButton("Question");
                btn.addActionListener(e -> {
                    var q = new QuestionPopup(d.getQuestion());
                    q.promptQuestion();
                });

                myPanel.add(btn);


                //Add spacer for between button/label pairs
                JLabel space = new JLabel(" ");
                space.setPreferredSize(new Dimension(WIDTH, 25));
                myPanel.add(space);
            }
            i++;
        }
        myPanel.revalidate();
        myPanel.repaint();
    }

    /**
     * @return an initialized sidebar panel.
     */
    JPanel getPanel() {
        myPanel = new JPanel();
        myPanel.setMaximumSize(new Dimension(WIDTH, TMPanel.GAME_SIZE));
        myPanel.setMinimumSize(new Dimension(WIDTH, TMPanel.GAME_SIZE));
        myPanel.setPreferredSize(new Dimension(WIDTH, TMPanel.GAME_SIZE));
        myPanel.setBackground(Color.GRAY);

        return myPanel;
    }
}
