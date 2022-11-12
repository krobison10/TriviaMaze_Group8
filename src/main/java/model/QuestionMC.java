package model;

/**
 * Multiple choice questions
 */
public class QuestionMC extends QuestionSelection {

    /**
     * Constructor that create a multiple choice question
     * @param thePrompt of the question
     * @param theOptions string array of the options for user to choose from
     * @param theAnswerPosition the index of the correct option
     */
    QuestionMC(String thePrompt, String[] theOptions, int theAnswerPosition) {
        super.setPrompt(thePrompt);
        super.setOptions(theOptions);
        super.setAnswerPosition(theAnswerPosition);
    }
}