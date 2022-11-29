/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.questions;

/**
 * Abstract class for Question in Multiple choice or True/False
 *
 * @author Minh Le
 */
public abstract class QuestionSelection extends Question {
    /**
     *
     */
    private String[] myOptions;
    /**
     *
     */
    private int myAnswerPosition;

    /**
     * Get the index of the correct answer
     * @return the int number of index
     */
    public int getAnswerPosition() {
        return myAnswerPosition;
    }

    /**
     * Set the index of the correct answer
     * @param theAnswerPosition the int number of index
     */
    protected void setAnswerPosition(final int theAnswerPosition) {
        myAnswerPosition = theAnswerPosition;
    }

    /**
     * Get the String array of the options that user can choose from
     * @return the String array of the options
     */
    public String[] getOptions() {
        return myOptions;
    }

    /**
     * Add the String array of the options to the question
     * @param theOptions the String array of the options
     */
    protected void setOptions(final String[] theOptions) {
        myOptions = theOptions;
    }
}
