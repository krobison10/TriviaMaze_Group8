package model;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
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

    /**
     * Represents the amount of questions currently stored.
     */
    private int size;

    /**
     * Constructs and stores a list of Question objects given a SQLite database filename.
     * @param theDBFilename the filename of the database.
     */
    QuestionBank(final String theDBFilename) {
        myQuestions = initializeQuestions(theDBFilename);
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
     * @return the quantity of questions currently stored.
     */
    int size() {
        return myQuestions.size();
    }

    /**
     * Queries all the rows of the questions table, builds and returns a list of
     * Question objects using that data.
     * @param theDBFilename the filename of the SQLite database.
     * @return a list of Question objects sourced from the database.
     */
    private List<Question> initializeQuestions(final String theDBFilename) {
        List<Question> questions = new ArrayList<>();

        SQLiteDataSource ds = getDataSource(theDBFilename);

        String query = "SELECT * FROM questions";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                questions.add(processEntry(rs));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        return questions;
    }

    /**
     * Using a row from the table, builds and returns a Question object.
     * @param theRS the result set that provides the data.
     * @return a Question object using the data in the row.
     * @throws SQLException if there is an error finding a column.
     */
    private Question processEntry(final ResultSet theRS) throws SQLException {
        //3 types, 1 represents MC, 2 represents TF, 3 represents FA
        int type = theRS.getInt("Type");
        String prompt = theRS.getString("Prompt");

        if(type == 3) {
            String answer = theRS.getString("Option_1");
            return QuestionFactory.createQuestion(prompt, answer);
        }

        int answerPos = theRS.getInt("Answer_Pos");
        String option1 = theRS.getString("Option_1");
        String option2 = theRS.getString("Option_2");

        String[] options = {option1, option2};

        //If multiple choice, add 2 more options to array
        if(type == 1) {
            String option3 = theRS.getString("Option_3");
            String option4 = theRS.getString("Option_4");
            options = new String[] {option1, option2, option3, option4};
        }
        return QuestionFactory.createQuestion(prompt, answerPos, options);
    }

    /**
     * @param theDBFilename the name of the database file.
     * @return the SQLiteDataSource built using the filename.
     */
    private SQLiteDataSource getDataSource(final String theDBFilename) {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:" + theDBFilename);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return ds;
    }
}
