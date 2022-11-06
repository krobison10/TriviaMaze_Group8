package model;

import java.util.ArrayList;

/**
 * Class for Question bank
 */
public class QuestionBank {
    private ArrayList<Question> myQuestions;

    public Question getQuestion(int theQuestionPosition){
        return myQuestions.get(theQuestionPosition);
    }
    public Question getRandomQuestion(int theRandomNumber){
        return getQuestion(theRandomNumber);
    }

}
