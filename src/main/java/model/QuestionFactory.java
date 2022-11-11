package model;

/**
 * Factory that creates appropriate instances of Question objects.
 */
class QuestionFactory {

    /**
     * Creates a Selection Question, meaning that the answer is chosen
     * by selecting it. Multiple choice and true/false questions fall
     * under this category.
     * @param thePrompt the prompt for the question.
     * @param theOptions the potential answer set for the question.
     * @param theAnswerIndex the position of the answer within the list of options.
     * @return an instance of the appropriate Question object.
     */
    static Question createQuestion
    (final String thePrompt, final int theAnswerIndex, final String... theOptions) {

        if(theOptions.length == 2) {
            return new QuestionTF(thePrompt, theOptions, theAnswerIndex);
        }

        if(theOptions.length > 2) {
            return new QuestionMC(thePrompt, theOptions, theAnswerIndex);
        }

        throw new IllegalArgumentException("Illegal Argument");
    }

    /**
     * Creates a free answer question, where the user types a response in words.
     * @param thePrompt the prompt for the question.
     * @param theAnswer the answer for the question.
     * @return an instance of the appropriate Question object.
     */
    static Question createQuestion(String thePrompt, String theAnswer) {
        return new QuestionFA(thePrompt,theAnswer);
    }
}
