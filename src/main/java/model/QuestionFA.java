package model;

/**
 * Class for Filling Answer Question Type
 */
public class QuestionFA extends Question {
    private String myPrompt;
    private String myAnswer;

    QuestionFA(String myPrompt, String myAnswer) {
        this.myPrompt = myPrompt;
        this.myAnswer = myAnswer;
    }

    public String getAnswer() {
        return this.myAnswer;
    }
}
