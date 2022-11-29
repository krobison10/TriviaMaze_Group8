/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.questions;

import java.util.Random;

/**
 * Class for Free Answer Question Type
 *
 * @author Minh Le
 */
public class QuestionFA extends Question {
    /**
     *
     */
    private final String myAnswer;

    /**
     * Constructor to create the Free answer question type
     * @param thePrompt of the question
     * @param theAnswer of the question
     */
    public QuestionFA(final String thePrompt, final String theAnswer) {
        super.setPrompt(thePrompt);
        myAnswer = theAnswer;
    }

    /**
     * Get the answer of the question
     * @return string of answer of the question
     */
    public String getAnswer() {
        return myAnswer;
    }

    /**
     * Display the hint with some letters from the answer
     */
    public void displayHint(){
        String answer = myAnswer;
        int lastIndex = answer.length() - 1;
        Random random = new Random();
        int randomNumber = random.nextInt(lastIndex);

        String hint = "";

        for(int i = 0; i <= lastIndex; i++){
            if(i == randomNumber){
                hint = hint + answer.charAt(i);
            } else {
                hint += "-";
            }
        }
        System.out.println(getPrompt());
        System.out.println(hint);
    }
}