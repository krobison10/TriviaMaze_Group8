/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import model.mazeElements.Player;
import model.mazeElements.Room;
import model.mazeElements.TriviaMaze;
import model.tiles.TileManager;
import view.BuildUI;

/**
 * Main controller class that handles initialization of game and central functions.
 *
 * @author Kyler Robison
 */
public class TriviaMazeController {
    /**
     * Global access point to the instance of TriviaMazeController.
     */
    public static TriviaMazeController instance;

    /**
     * Initializes and starts the game.
     */
    public TriviaMazeController() {
        initialize();

        BuildUI.instance.buildFrame();
    }

    /**
     * Creates a Game object and starts the process.
     */
    public void startNewGame() {
        Game game = new Game();
        game.start();
    }

    /**
     * Series of calls for basic initialization of model and view.
     */
    private void initialize() {
        instance = this;
        new TriviaMaze(5, 5, "CS_trivia_questions.db");
        new Player();
        new PlayerController();
        new TileManager();
        new BuildUI();
        //Instance of TMPanel created with legitimate singleton
    }

    //Not yet implemented
    void updateCurrentRoom(final Room theRoom) {

    }
}
