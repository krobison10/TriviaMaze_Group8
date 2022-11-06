package model;

/**
 * Question Factory that create new question
 */
public class QuestionFactory {

    // Create Multiple Choice and True/False question
    public Question createQuestion(String thePrompt, String[] theOption, int correctAnswerIndex)
    {
        if(theOption.length == 4)
        {
            return new QuestionMC(thePrompt,theOption,correctAnswerIndex);
        }
        if(theOption.length == 2)
        {
            return new QuestionTF(thePrompt,theOption,correctAnswerIndex);
        }
        else{
            throw new IllegalArgumentException("Illegal Argument");
        }
    }

    // Create Filling Answer
    public Question createQuestion(String thePrompt, String theAnswer)
    {
            return new QuestionFA(thePrompt,theAnswer);
    }
}
