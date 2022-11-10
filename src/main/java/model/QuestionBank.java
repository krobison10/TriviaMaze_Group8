package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the pool of questions for the game. Reads from a SQLite database
 * to collect data, then builds and stores a list of Question objects from it.
 */
public class QuestionBank {
    /**
     * The list of Question objects sourced from the database.
     */
    private final List<Question> myQuestions;


    QuestionBank() {
        myQuestions = new ArrayList<>();
        initializeQuestions();
    }

    /**
     * Adds a new Question to the list.
     * @param theQuestion the Question to be added.
     */
    void addQuestion(final Question theQuestion) {
        myQuestions.add(theQuestion);
    }

    /**
     * Removes the first occurrence of a Question from the list.
     * @param theQuestion the Question to be removed.
     */
    void removeQuestion(final Question theQuestion) {
        if(!myQuestions.isEmpty()) {
            myQuestions.remove(theQuestion);
        }
    }

    /**
     * Gets a question at a certain index in the list.
     * @param thePos the index.
     * @return the Question object
     */
    Question getQuestion(final int thePos) {
        return myQuestions.remove(thePos);
    }

    /**
     * Gets and removes a random Question from the list.
     * @return the randomly selected Question.
     */
    Question getRandomQuestion() {
        Random rnd = new Random();
        return getQuestion(rnd.nextInt(0, myQuestions.size()));
    }

    /**
     * Reads from a database and adds Question objects to the list.
     */
    private void initializeQuestions() {
        //DB functions go here
    }
}
