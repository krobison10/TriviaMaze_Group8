/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.questions;

/**
 * Abstract class for Question
 *
 * @author Minh Le
 */
public abstract class Question {
    /**
     *
     */
    private String myPrompt;

    /**
     * Get the prompt of the question
     * @return the prompt of the question
     */
    public String getPrompt() {
        return myPrompt;
    }

    /**
     * Add prompt to the question
     * @param thePrompt to be added to the question
     */
    protected void setPrompt(final String thePrompt) {
        myPrompt = thePrompt;
    }
}
