package model;

import java.util.Random;

/**
 * Class for Free Answer Question Type
 */
public class QuestionFA extends Question {
    private String myAnswer;

    /**
     * Constructor to create the Free answer question type
     * @param thePrompt of the question
     * @param theAnswer of the question
     */
    QuestionFA(String thePrompt, String theAnswer) {
        super.setPrompt(thePrompt);
        this.myAnswer = theAnswer;
    }

    /**
     * Get the answer of the question
     * @return string of answer of the question
     */
    public String getAnswer() {
        return this.myAnswer;
    }

    /**
     * Display the hint with some letters from the answer
     */
    public void displayHint(){
        String answer = this.myAnswer;
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
        System.out.println(this.getPrompt());
        System.out.println(hint);
    }
}