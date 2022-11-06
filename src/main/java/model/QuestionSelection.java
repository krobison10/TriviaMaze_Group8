package model;

/**
 * Abstract class for Question in Multiple choice or True/False
 */
public abstract class QuestionSelection extends Question {
    private int myAnswerPosition;

    public int getAnswerPosition() {
        return this.myAnswerPosition;
    }
}
