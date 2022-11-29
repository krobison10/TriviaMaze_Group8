package view;

import controller.PlayerController;
import model.mazeElements.Door;
import model.mazeElements.DoorStates;
import model.mazeElements.Room;
import model.mazeElements.TriviaMaze;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SidebarManager {

    private static SidebarManager instance;

    private JPanel myPanel;

    private ArrayList<Component> components;

    private SidebarManager() {
        components = new ArrayList<>();
    }

    public static SidebarManager getInstance() {
        if(instance == null) {
            instance = new SidebarManager();
        }
        return instance;
    }

    public void updateForCurrentRoom() {
        //Stores the directions of the doors
        String[] directions = {"East", "North", "West", "South"};

        Room r = TriviaMaze.getInstance().getRoom(0, 0);

        //Add first spacer
        JLabel spacer = new JLabel(" ");
        spacer.setPreferredSize(new Dimension(300, 50));
        myPanel.add(spacer);
        components.add(spacer);

        int i = 0;
        for(Door d : r.getDoors()) {
            if(d != null && d.getState() == DoorStates.CLOSED) {

                //Create and add label
                JLabel label = new JLabel(directions[i] + " door");
                label.setPreferredSize(new Dimension(300, 25));
                label.setHorizontalAlignment(SwingConstants.CENTER);

                myPanel.add(label);
                components.add(label);


                //Create and add button
                JButton btn = new JButton("Question");
                btn.addActionListener(e -> {
                    var q = new QuestionPopup(d.getQuestion());
                    q.promptQuestion();
                });

                myPanel.add(btn);
                components.add(btn);


                //Add spacer for between button/label pairs
                JLabel space = new JLabel(" ");
                space.setPreferredSize(new Dimension(300, 25));
                myPanel.add(space);
                components.add(space);
            }
            i++;
        }
    }

    JPanel getPanel() {
        myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(300, TMPanel.GAME_SIZE));
        return myPanel;
    }

}
