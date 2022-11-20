package model.mazeElements;


import model.mazeElements.TriviaMaze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriviaMazeTest {

    @Test
    @DisplayName("Maze: retreiving out of bounds room throws exception")
    void getRoomOutOfBounds() {
        var maze = new TriviaMaze(2, 2, "test_questions.db");
        assertThrows(IndexOutOfBoundsException.class, () -> maze.getRoom(-1, 1),
                "X out of bounds, too small");
        assertThrows(IndexOutOfBoundsException.class, () -> maze.getRoom(1, -1),
                "Y out of bounds, too small");
        assertThrows(IndexOutOfBoundsException.class, () -> maze.getRoom(2, 1),
                "X out of bounds, too large");
        assertThrows(IndexOutOfBoundsException.class, () -> maze.getRoom(1, 2),
                "Y out of bounds, too large");
    }

    @Test
    @DisplayName("Maze: add door")
    void addDoor() {
        var maze = new TriviaMaze(2, 2, "test_questions.db");
        var newDoor = maze.getRoom(0,0).getDoors().get(2);
        assertEquals(4, maze.getAllDoors().size());
        maze.addDoor(newDoor);
        assertEquals(5, maze.getAllDoors().size());
    }
}