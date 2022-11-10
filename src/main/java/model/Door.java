package model;

/**
 * Represents a door in the maze. Is a bridge between rooms. All doors contain a question and a
 * state that determines how the door will behave. Certain rooms will share a door object when they
 * are adjacent.
 */
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
    /**
     * Reference to the containing TriviaMaze object. Field may be redundant.
     */
    private final TriviaMaze myMaze;


    /**
     * Constructs a door and assigns it a random question from the question bank, initializes
     * state to closed.
     */
    Door(final TriviaMaze theMaze) {
        myMaze = theMaze;
        myState = DoorStates.CLOSED;
        //myQuestion = theMaze.questionBank().getRandomQuestion(); //QuestionBank not finished yet
    }

    /**
     * @return the question contained by the door.
     */
    public Question getQuestion() {
        return myQuestion;
    }

    /**
     * @return the current state of the door.
     */
    public DoorStates getState() {
        return myState;
    }

    /**
     * updates the state of the door to whatever is passed in.
     * @param theState the new state of the door.
     * @return the updated state.
     */
    public DoorStates setState(final DoorStates theState) {
        myState = theState;
        return myState;
    }

    /**
     * Changes the question contained by the door.
     * @param theQuestion the new question.
     * @return the updated question.
     */
    Question setQuestion(final Question theQuestion) {
        myQuestion = theQuestion;
        return myQuestion;
    }
}
