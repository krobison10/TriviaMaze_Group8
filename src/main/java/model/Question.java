package model;

/**
 * Abstract class for Question
 */
public abstract class Question {
    private String myPrompt;

    /**
     * Get the prompt of the question
     * @return the prompt of the question
     */
    public String getPrompt() {
        return this.myPrompt;
    }

    /**
     * Add prompt to the question
     * @param thePrompt to be added to the question
     */
    protected void setPrompt(String thePrompt) {
        this.myPrompt = thePrompt;
    }
}
