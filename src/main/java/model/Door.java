package model;

public class Door {
    /**
     * Represents the question that the door contains
     */
    private Question myQuestion;
    /**
     * Represents the state of the door. A door can be closed, meaning that
     * the user has not yet correctly answered the question, nor incorrectly, and
     * therefore is not permitted to pass through yet. It can be opened if the user
     * answered the question correctly, allowing the player to pass through. Or it can be
     * blocked, meaning that the question was answered incorrectly and the door
     * will be un-passable for the remainder of the game.
     */
    private DoorStates myState;


    Door(final Question theQuestion) {
        myQuestion = theQuestion;
    }

    /**
     * Constructs a door and assigns it a random question from the question bank.
     */
    Door() {
        //myQuestion = QuestionBank.getRandomQuestion()
    }

    public Question getQuestion() {
        return myQuestion;
    }

    private Question ChangeQuestion(final Question theQuestion) {
        myQuestion = theQuestion;
        return myQuestion;
    }

    public DoorStates getState() {
        return myState;
    }

    public DoorStates setState(final DoorStates theState) {
        myState = theState;
        return myState;
    }


}
