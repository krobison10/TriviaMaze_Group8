package model.mazeElements;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriviaMazeTest {

    @Test
    @DisplayName("Maze: retreiving out of bounds room returns null")
    void getRoomOutOfBounds() {
        var maze = new TriviaMaze(2, 2, "CS_trivia_questions.db");
        assertNull(maze.getRoom(-1, 1), "X out of bounds, too small");
        assertNull(maze.getRoom(1, -1), "Y out of bounds, too small");
        assertNull(maze.getRoom(2, 1), "X out of bounds, too large");
        assertNull(maze.getRoom(1, 2), "Y out of bounds, too large");

    }

    @Test
    @DisplayName("Maze: add door")
    void addDoor() {
        var maze = new TriviaMaze(2, 2, "CS_trivia_questions.db");
        var newDoor = maze.getRoom(0,0).getDoors().get(2);
        assertEquals(4, maze.getAllDoors().size());
        maze.addDoor(newDoor);
        assertEquals(5, maze.getAllDoors().size());
    }

    @Test
    @DisplayName("Maze: path to exit")
    void testPathToExit() {
        TriviaMaze maze = new TriviaMaze(4, 4, "CS_trivia_questions.db");
        /*
            Builds a maze with the following structure
            x represents a blocked door
            oxo-o-o
            | | | |
            oxoxoxo
            | | x |
            oxo-oxo
            | x | |
            o-o-oxo
            this maze contains a valid path
         */

        setDoorState(0, 0, 2, maze);
        setDoorState(0, 1, 2, maze);
        setDoorState(0, 2, 2, maze);
        setDoorState(1, 2, 3, maze);
        setDoorState(2, 1, 0, maze);
        setDoorState(2, 1, 2, maze);
        setDoorState(2, 1, 3, maze);
        setDoorState(2, 2, 2, maze);
        setDoorState(3, 3, 0, maze);
        assertTrue(maze.existsPathToExit());

        //Blocks a door that is necessary to the only valid exit bath
        setDoorState(1, 0, 2, maze);
        assertFalse(maze.existsPathToExit());
    }

    void setDoorState(int x, int y, int doorNum, TriviaMaze maze) {
        maze.getRoom(x, y).getDoors().get(doorNum).setState(DoorStates.BLOCKED);
    }
}