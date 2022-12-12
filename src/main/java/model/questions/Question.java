/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.questions;

import java.io.Serializable;

/**
 * Abstract class for Question
 *
 * @author Minh Le
 */
public abstract class Question implements Serializable {
    /**
     * Prompt of the question
     */
    private String myPrompt;

    /**
     * @return the answer for the question.
     */
    public abstract String getAnswer();

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
