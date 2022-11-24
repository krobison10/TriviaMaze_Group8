package controller;

import model.mazeElements.Room;
import model.mazeElements.TriviaMaze;
import view.BuildUI;
import view.TMPanel;

public class TriviaMazeController {
    /**
     * Reference to the main class of the model package
     */
    private TriviaMaze myMaze;
    /**
     * Reference to the main class of the view package
     */
    private BuildUI myUI;

    public TriviaMazeController() {
        initialize();
    }

    private void initialize() {
        myMaze = new TriviaMaze(5, 5, "CS_trivia_questions.db");
        myUI = new BuildUI(this);

        myUI.buildFrame();
    }

    public void startNewGame(final TMPanel thePanel) {
        Game game = new Game(myMaze, thePanel);
        game.start();
    }

    void updateCurrentRoom(final Room theRoom) {

    }

    private boolean promptQuestion() {
        return false;
    }
}
