package model;

import java.util.ArrayList;

/**
 * Class for Question bank
 */
public class QuestionBank {
    private ArrayList<Question> myQuestionBank;

    // Constructor that create a question bank
    public QuestionBank() {
        ArrayList<Question> myQuestionBank= new ArrayList<Question>();
    }

    // Add Question to the question bank
    public void addQuestion(Question theQuestion){
            myQuestionBank.add(theQuestion);
    }

    // Remove Question from the inventory
    public void removeQuestion(Question theQuestion){
        if(myQuestionBank.size() > 0){
            myQuestionBank.remove(theQuestion);
        }
        else {
            System.out.println("Question Bank is empty. Cannot remove Question.");
        }
    }
    public Question getQuestion(int theQuestionPosition){
        return myQuestionBank.get(theQuestionPosition);
    }
    public Question getRandomQuestion(int theRandomNumber){
        return getQuestion(theRandomNumber);
    }
}
