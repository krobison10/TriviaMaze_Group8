package model.questions;

/**
 * Abstract class for Question in Multiple choice or True/False
 */
public abstract class QuestionSelection extends Question {
    private String[] myOptions;
    private int myAnswerPosition;

    /**
     * Get the index of the correct answer
     * @return the int number of index
     */
    public int getAnswerPosition() {
        return this.myAnswerPosition;
    }

    /**
     * Set the index of the correct answer
     * @param theAnswerPosition the int number of index
     */
    protected void setAnswerPosition(int theAnswerPosition) {
        this.myAnswerPosition = theAnswerPosition;
    }

    /**
     * Get the String array of the options that user can choose from
     * @return the String array of the options
     */
    public String[] getOptions() {
        return this.myOptions;
    }

    /**
     * Add the String array of the options to the question
     * @param theOptions the String array of the options
     */
    protected void setOptions(String[] theOptions) {
        this.myOptions = theOptions;
    }
}
