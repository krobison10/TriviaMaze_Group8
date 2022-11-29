/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.questions;

/**
 * Multiple choice questions
 *
 * @author Minh Le
 */
public class QuestionMC extends QuestionSelection {

    /**
     * Constructor that create a multiple choice question
     * @param thePrompt of the question
     * @param theOptions string array of the options for user to choose from
     * @param theAnswerPosition the index of the correct option
     */
    public QuestionMC(final String thePrompt, final String[] theOptions, final int theAnswerPosition) {
        super.setPrompt(thePrompt);
        super.setOptions(theOptions);
        super.setAnswerPosition(theAnswerPosition);
    }
}