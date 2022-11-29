/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.questions;

/**
 * True False questions
 *
 * @author Minh Le
 */
public class QuestionTF extends QuestionSelection {

    /**
     * Constructor that create a multiple choice question
     * @param thePrompt of the question
     * @param theOptions string array of the options for user to choose from
     * @param theAnswerPosition the index of the correct option
     */
    public QuestionTF(final String thePrompt, final String[] theOptions, final int theAnswerPosition) {
        super.setPrompt(thePrompt);
        super.setOptions(theOptions);
        super.setAnswerPosition(theAnswerPosition);
    }
}