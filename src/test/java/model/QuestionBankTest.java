package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class QuestionBankTest {

    static Method processEntry, initializeQuestions;
    static QuestionBank qb;

    @BeforeAll
    static void init() throws NoSuchMethodException {
        qb = new QuestionBank("questions.db");

        //Reflection to access private methods
        initializeQuestions = qb.getClass().getDeclaredMethod("initializeQuestions", String.class);
        initializeQuestions.setAccessible(true);

        processEntry = qb.getClass().getDeclaredMethod("processEntry", ResultSet.class);
        processEntry.setAccessible(true);

    }

    @Test
    void addQuestion() {

    }

    @Test
    void removeQuestion() {

    }

    @Test
    void getQuestion() {

    }
}