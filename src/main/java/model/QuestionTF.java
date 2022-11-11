package model;

/**
 * True False questions
 */
public class QuestionTF extends QuestionSelection {
    private String myPrompt;
    private String[] myOptions;
    private int myAnswerPosition;

    QuestionTF(String myPrompt, String[] myOptions, int myAnswerPosition) {
        this.myPrompt = myPrompt;
        this.myOptions = myOptions;
        this.myAnswerPosition = myAnswerPosition;
    }

    public String[] getOptions() {
        return this.myOptions;
    }
    public int getAnswerPosition() {
        return this.myAnswerPosition;
    }
}
