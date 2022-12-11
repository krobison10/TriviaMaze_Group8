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
     *
     */
    private String myPrompt;


    /**
     * Returns the answer string of any type of question object.
     * @param theQuestion the Question object.
     * @return the answer string.
     */
    public static String getAnswerString(final Question theQuestion) {
        String answer;
        if(theQuestion instanceof QuestionSelection) {
            var q = (QuestionSelection) theQuestion;
            answer = q.getOptions()[q.getAnswerPosition()];
        }
        else {
            var q = (QuestionFA) theQuestion;
            answer = q.getAnswer();
        }
        return answer;
    }

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
