package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionBankTest {

    static Method initializeQuestions;
    static QuestionBank qb;

    @BeforeAll
    static void init() throws NoSuchMethodException {
        qb = new QuestionBank("test_questions.db");

        //Reflection to access private methods
        initializeQuestions = qb.getClass().getDeclaredMethod("initializeQuestions", String.class);
        initializeQuestions.setAccessible(true);

    }
    @Test
    @DisplayName("QuestionBank: read question objects from database")
    void initializeQuestions() throws Exception {
        List<Question> questions = (List<Question>) initializeQuestions.invoke(qb, "test_questions.db");
        assertEquals(3, questions.size());
    }
    @Test
    @DisplayName("QuestionBank: add question")
    void addQuestion() {
        qb = new QuestionBank("test_questions.db");
        int size = qb.size();
        qb.addQuestion(new QuestionFA("", ""));
        assertEquals(size + 1, qb.size());
    }

    @Test
    @DisplayName("QuestionBank: remove question")
    void removeQuestion() {
        qb = new QuestionBank("test_questions.db");
        int size = qb.size();
        Question q = qb.getQuestion(0);
        qb.removeQuestion(q);
        assertEquals(size - 1, qb.size());
    }

    @Test
    @DisplayName("QuestionBank: remove random question")
    void getRandomQuestion() {
        qb = new QuestionBank("test_questions.db");
        int size = qb.size();
        qb.getRandomQuestion();
        assertEquals(size - 1, qb.size());
    }
}