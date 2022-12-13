package model.questions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for QuestionTF class
 *
 * @author Minh Le
 */
class QuestionTFTest {
    private static QuestionTF myQuestionTF;
    private static String myPrompt;
    private static String[] myOptions;
    private static int myAnswerPosition;

    @BeforeEach
    void setUpBeforeClass() {
        myPrompt = "Is Canada the largest country in the North America?";
        myOptions = new String[] {"True", "False"};
        myAnswerPosition = 0;
        myQuestionTF = new QuestionTF(myPrompt, myOptions, myAnswerPosition);
    }

    @AfterEach
    void tearDown() {
    }


    /**
     * Test to get answer
     */
    @Test
    @DisplayName("Test to get answer")
    void testGetAnswer() {
        assertEquals("Canada",myQuestionTF.getAnswer(),
                "The answer should be Canada.");
    }

    /**
     * Test to get answer position
     */
    @Test
    @DisplayName("Test to get answer position")
    void getAnswerPosition() {
        assertEquals(0,myQuestionTF.getAnswerPosition(),
                "The answer position should be 0.");
    }

    /**
     * Test to set answer position
     */
    @Test
    @DisplayName("Test to get answer position")
    void setAnswerPosition() {
        myQuestionTF.setAnswerPosition(1);
        assertEquals(1,myQuestionTF.getAnswerPosition(),
                "The answer position should be 1.");
    }

    /**
     * Test to get options
     */
    @Test
    @DisplayName("Test to get options")
    void getOptions() {
        assertEquals(myOptions,myQuestionTF.getOptions(),
                "The return option is not correct.");
    }

    /**
     * Test to set options
     */
    @Test
    @DisplayName("Test to set options")
    void setOptions() {
        String[] newOptions = new String[] {"False", "True"};
        myQuestionTF.setOptions(newOptions);

        assertEquals(newOptions,myQuestionTF.getOptions(),
                "The return option is not correct.");
    }

    /**
     * Test to get prompt
     */
    @Test
    @DisplayName("Test to get prompt")
    void getPrompt() {
        assertEquals(myPrompt,myQuestionTF.getPrompt(),
                "The return prompt is not correct.");
    }

    /**
     * Test to set prompt
     */
    @Test
    @DisplayName("Test to get prompt")
    void setPrompt() {
        String newPrompt = "This is a new prompt";
        myQuestionTF.setPrompt(newPrompt);

        assertEquals(newPrompt,myQuestionTF.getPrompt(),
                "The return prompt is not correct.");
    }
}