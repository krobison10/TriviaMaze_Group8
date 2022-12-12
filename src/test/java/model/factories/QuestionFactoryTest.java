package model.factories;

import model.questions.Question;
import model.questions.QuestionFA;
import model.questions.QuestionMC;
import model.questions.QuestionTF;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionFactoryTest {

    @Test
    @DisplayName("QuestionFactory: Create a T/F question")
    void createQuestion1() {
        Question q = QuestionFactory.createQuestion("Prompt", 0, "True", "False");
        assertTrue(q instanceof QuestionTF, "Wrong subtype: expected T/F question but got " + q.getClass());
    }

    @Test
    @DisplayName("QuestionFactory: Create an MC question")
    void createQuestion2() {
        Question q = QuestionFactory.createQuestion("Prompt", 0, "a", "b", "c", "d");
        assertTrue(q instanceof QuestionMC, "Wrong subtype: expected MC question but got " + q.getClass());
    }

    @Test
    @DisplayName("QuestionFactory: Create an FA question")
    void createQuestion3() {
        Question q = QuestionFactory.createQuestion("Prompt", "Answer");
        assertTrue(q instanceof QuestionFA, "Wrong subtype: expected FA question but got " + q.getClass());
    }
}