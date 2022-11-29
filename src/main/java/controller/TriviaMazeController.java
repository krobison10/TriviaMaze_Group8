/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import model.mazeElements.Room;
import model.mazeElements.TriviaMaze;
import view.BuildUI;

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

    //Not yet implemented
    void updateCurrentRoom(final Room theRoom) {

    }
}
