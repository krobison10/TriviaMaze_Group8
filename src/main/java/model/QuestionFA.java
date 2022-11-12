package model;

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
}