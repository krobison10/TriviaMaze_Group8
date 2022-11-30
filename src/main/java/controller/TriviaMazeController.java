/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import javax.swing.*;

import model.mazeElements.*;
import model.questions.Question;
import model.tiles.TileManager;
import view.BuildUI;
import view.SidebarManager;
import view.TMPanel;

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
        new TriviaMaze(5, 5, "CS_trivia_questions.db");
        Game game = new Game();
        game.start();
    }

    /**
     * Processes the answer attempt and executes necessary side effects.
     * Makes a call to TileManager to update the door tile. Updates the
     * door state in model. And calls the algorithm to check whether
     * there is still a path to the exit. Ends the game if there is no
     * longer a path.
     * @param theQuestion the Question the user is answering.
     * @param theAnswer the answer that the user submitted.
     */
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
            JOptionPane.showMessageDialog(TMPanel.getTriviaMaze(), "Game over :(");
        }
    }

    /**
     * Tells the sidebar manager to update its display for the current room.
     */
    void enteredNewRoom() {
        SidebarManager.getInstance().updateForCurrentRoom();
    }

    /**
     * Checks if the player is in a new room by comparing the new room passed into this
     * method with the room object currently stored in the player. If the room is new,
     * updates the players room object.
     * @param theRoom the current room from controller to check against the player's saved
     *                room.
     * @return True if the room is a new room, false otherwise.
     */
    boolean playerInNewRoom(final Room theRoom) {
        boolean newRoom = theRoom != Player.getInstance().getCurrentRoom();
        if(newRoom) {
            Player.getInstance().setCurrentRoom(theRoom);
        }
        return newRoom;
    }
}
