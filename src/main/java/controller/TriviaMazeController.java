/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import model.mazeElements.*;
import model.questions.Question;
import model.questions.QuestionFA;
import model.questions.QuestionMC;
import model.questions.QuestionSelection;
import model.tiles.Tile;
import model.tiles.TileManager;
import view.BuildUI;
import view.SidebarManager;

import javax.swing.*;

/**
 * Main controller class that handles initialization of game and central functions.
 *
 * @author Kyler Robison
 */
public class TriviaMazeController {
    /**
     * Singleton variable.
     */
    private static TriviaMazeController instance;

    /**
     * Initializes and starts the game.
     */
    private TriviaMazeController() {
        new TriviaMaze(5, 5, "CS_trivia_questions.db");
        BuildUI.getInstance().buildFrame();
    }

    /**
     * Global access point to the instance of TriviaMazeController.
     */
    public static TriviaMazeController getInstance() {
        if(instance == null) {
            instance = new TriviaMazeController();
        }
        return instance;
    }

    /**
     * Creates a Game object and starts the process.
     */
    public void startNewGame() {
        Game game = new Game();
        game.start();
    }

    public void processAnswerAttempt(final Question theQuestion, final String theAnswer ) {
        String correctAnswer = Question.getAnswerString(theQuestion);
        Door door = TriviaMaze.getInstance().getDoor(theQuestion);
        if(correctAnswer.equalsIgnoreCase(theAnswer.trim())) {
            door.setState(DoorStates.OPENED);
        }
        else {
            door.setState(DoorStates.BLOCKED);
        }
        TileManager.getInstance().updateDoorTile(door);
        SidebarManager.getInstance().updateForCurrentRoom();

        if(!TriviaMaze.getInstance().existsPathToExit()) {
            JOptionPane.showMessageDialog(null, "Game over :(");
        }
    }



    void enteredNewRoom() {
        SidebarManager.getInstance().updateForCurrentRoom();
    }

    boolean playerInNewRoom(final Room theRoom) {
        boolean newRoom = theRoom != Player.getInstance().getCurrentRoom();
        if(newRoom) {
            Player.getInstance().setCurrentRoom(theRoom);
            if(theRoom != null) {
                System.out.println("New not null room");
            }
        }
        return newRoom;
    }

    private void updateDoorTile() {

    }
}
