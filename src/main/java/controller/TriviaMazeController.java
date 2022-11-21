package controller;

import model.mazeElements.TriviaMaze;
import view.BuildGUI;

public class TriviaMazeController {
    /**
     * Reference to the main class of the model package
     */
    private TriviaMaze myMaze;
    /**
     * Reference to the main class of the view package
     */
    private BuildGUI myUI;

    public TriviaMazeController() {
        initialize();
    }

    private void initialize() {
        myMaze = new TriviaMaze
                (5, 5, "CS_trivia_questions.db");
        myUI = new BuildGUI();

        myUI.buildFrame();
    }

    private boolean gameLoop() {
        return false;
    }

    private boolean promptQuestion() {
        return false;
    }
}
