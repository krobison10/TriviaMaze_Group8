package model.questions;

/**
 * True False questions
 */
public class QuestionTF extends QuestionSelection {

    /**
     * Constructor that create a multiple choice question
     * @param thePrompt of the question
     * @param theOptions string array of the options for user to choose from
     * @param theAnswerPosition the index of the correct option
     */
    public QuestionTF(String thePrompt, String[] theOptions, int theAnswerPosition) {
        super.setPrompt(thePrompt);
        super.setOptions(theOptions);
        super.setAnswerPosition(theAnswerPosition);
    }
}