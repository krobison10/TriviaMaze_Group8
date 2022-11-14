package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class QuestionFATest {

    private QuestionFA myQuestionFA;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    @DisplayName("Test to get prompt")
    void testGetPrompt() {
        String prompt = "What is the largest rainforest in the world?";
        String answer = "Amazon";
        myQuestionFA = new QuestionFA(prompt, answer);
        assertEquals("What is the largest rainforest in the world?",myQuestionFA.getPrompt(),
                "The prompt is not correct.");
    }

    @Test
    void setPrompt() {
    }

    @Test
    @DisplayName("Test to get Answer")
    void getAnswer() {
        String prompt = "What is the largest rainforest in the world?";
        String answer = "Amazon";
        myQuestionFA = new QuestionFA(prompt, answer);
        assertEquals("Amazon",myQuestionFA.getAnswer(),
                "The answer is not correct.");
    }

    @Test()
    @DisplayName("Display hints for the question 1")
    void testDisplayHint1() {
        String prompt = "What is the largest rainforest in the world?";
        String answer = "Amazon";
        myQuestionFA = new QuestionFA(prompt, answer);
        myQuestionFA.displayHint();
        System.out.println(myQuestionFA.getAnswer());
    }

    @Test()
    @DisplayName("Display hints for the question 2")
    void testDisplayHint2() {
        String prompt = "What is the largest country in the North America?";
        String answer = "Canada";
        myQuestionFA = new QuestionFA(prompt, answer);
        myQuestionFA.displayHint();
        System.out.println(myQuestionFA.getAnswer());
    }
}