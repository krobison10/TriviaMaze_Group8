package controller;

import model.mazeElements.Player;
import model.mazeElements.Room;
import model.mazeElements.TriviaMaze;
import model.tiles.TileManager;
import view.BuildUI;

public class TriviaMazeController {
    /**
     * Global access point to the instance of TriviaMazeController.
     */
    public static TriviaMazeController instance;

    public TriviaMazeController() {
        initialize();
    }

    private void initialize() {
        instance = this;
        new TriviaMaze(5, 5, "CS_trivia_questions.db");
        new Player();
        new PlayerController();
        new TileManager();
        new BuildUI();
        //Instance of TMPanel created with legitimate singleton

        BuildUI.instance.buildFrame();
    }

    public void startNewGame() {
        Game game = new Game();
        game.start();
    }

    void updateCurrentRoom(final Room theRoom) {

    }

    private boolean promptQuestion() {
        return false;
    }
}
