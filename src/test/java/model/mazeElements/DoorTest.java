package model.mazeElements;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {
    private static Door door;

    @BeforeAll
    static void init() {
        new TriviaMaze(4, 4, "CS_trivia_questions.db");
        door = TriviaMaze.getInstance().getAllDoors().get(0);
        assertEquals(DoorStates.CLOSED, door.getState(), "Door: state initialized incorrectly");
    }

    @Test
    @DisplayName("Door: get question")
    void getQuestion() {
        assertNotNull(door.getQuestion(), "Door: new door has no question");
    }

    @Test
    @DisplayName("Door: set state")
    void setState() {
        door.setState(DoorStates.BLOCKED);
        assertEquals(DoorStates.BLOCKED, door.getState(), "Door: set state blocked");

        door.setState(DoorStates.CLOSED);
        assertEquals(DoorStates.CLOSED, door.getState(), "Door: set state closed");

        door.setState(DoorStates.OPENED);
        assertEquals(DoorStates.OPENED, door.getState(), "Door: set state opened");
    }

    @Test
    @DisplayName("Door: get state")
    void getState() {
        door.setState(DoorStates.BLOCKED);
        assertEquals(DoorStates.BLOCKED, door.getState(), "Door: set state blocked");

        door.setState(DoorStates.CLOSED);
        assertEquals(DoorStates.CLOSED, door.getState(), "Door: set state closed");

        door.setState(DoorStates.OPENED);
        assertEquals(DoorStates.OPENED, door.getState(), "Door: set state opened");

    }
}