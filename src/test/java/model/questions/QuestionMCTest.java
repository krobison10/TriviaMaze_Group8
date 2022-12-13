package model.questions;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for QuestionMC class
 *
 * @author Minh Le
 */
class QuestionMCTest {
    private static QuestionMC myQuestionMC;
    private static String myPrompt;
    private static String[] myOptions;
    private static int myAnswerPosition;

    @BeforeEach
    void setUpBeforeClass() {
        myPrompt = "What is the largest country in the North America?";
        myOptions = new String[] {"Canada", "United States", "Mexico", "Brazil"};
        myAnswerPosition = 0;
        myQuestionMC = new QuestionMC(myPrompt, myOptions, myAnswerPosition);
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
        assertEquals("Canada",myQuestionMC.getAnswer(),
                "The answer should be Canada.");
    }

    /**
     * Test to get answer position
     */
    @Test
    @DisplayName("Test to get answer position")
    void getAnswerPosition() {
        assertEquals(0,myQuestionMC.getAnswerPosition(),
                "The answer position should be 0.");
    }

    /**
     * Test to set answer position
     */
    @Test
    @DisplayName("Test to get answer position")
    void setAnswerPosition() {
        myQuestionMC.setAnswerPosition(1);
        assertEquals(1,myQuestionMC.getAnswerPosition(),
                "The answer position should be 1.");
    }

    /**
     * Test to get options
     */
    @Test
    @DisplayName("Test to get options")
    void getOptions() {
        assertEquals(myOptions,myQuestionMC.getOptions(),
                "The return option is not correct.");
    }

    /**
     * Test to set options
     */
    @Test
    @DisplayName("Test to set options")
    void setOptions() {
        String[] newOptions = new String[] {"Canada 1", "United States 1", "Mexico 1", "Brazil 1"};
        myQuestionMC.setOptions(newOptions);

        assertEquals(newOptions,myQuestionMC.getOptions(),
                "The return option is not correct.");
    }

    /**
     * Test to get prompt
     */
    @Test
    @DisplayName("Test to get prompt")
    void getPrompt() {
        assertEquals(myPrompt,myQuestionMC.getPrompt(),
                "The return prompt is not correct.");
    }

    /**
     * Test to set prompt
     */
    @Test
    @DisplayName("Test to get prompt")
    void setPrompt() {
        String newPrompt = "This is a new prompt";
        myQuestionMC.setPrompt(newPrompt);

        assertEquals(newPrompt,myQuestionMC.getPrompt(),
                "The return prompt is not correct.");
    }
}