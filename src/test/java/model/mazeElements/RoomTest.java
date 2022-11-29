package model.mazeElements;

import model.items.Item;
import model.items.ItemQuestionChange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test()
    @DisplayName("Room: Create Room, out of bounds throws exception")
    void testCreateDoorOutBounds() {
        new TriviaMaze(5, 5, "test_questions.db");
        assertThrows(IllegalArgumentException.class,
                () -> new Room(5, 3), "X too large");

        assertThrows(IllegalArgumentException.class,
                () -> new Room(2, 5), "Y too large");

        assertThrows(IllegalArgumentException.class,
                () -> new Room(5, 5), "X, Y too large");

        assertThrows(IllegalArgumentException.class,
                () -> new Room(-1, 3), "X too small");

        assertThrows(IllegalArgumentException.class,
                () -> new Room(-1, 5), "Y too small");

        assertThrows(IllegalArgumentException.class,
                () -> new Room(-1, -2), "X, Y too small");
    }

    @Test
    @DisplayName("Room: correct x and y positions")
    void testGetXandY() {
        var maze = new TriviaMaze(1, 1, "test_questions.db");
        var room = maze.getRoom(0, 0);
        assertEquals(room.getX(), 0, "Wrong X value");
        assertEquals(room.getY(), 0, "Wrong Y value");
    }

    @Test
    @DisplayName("Room: add item when empty")
    void testAddItemWhenEmpty() {
        var maze = new TriviaMaze(1, 1, "test_questions.db");
        var room = maze.getRoom(0, 0);
        assertNull(room.getItem(), "New room object already had item");
        assertTrue(room.addItem(new ItemQuestionChange("", "", "")),
                "Didn't return true on successful add");
        assertNotNull(room.getItem(), "Item null after adding item");
    }

    @Test
    @DisplayName("Room: add item when not empty, keeps existing item")
    void testAddItemWhenNotEmpty() {
        var maze = new TriviaMaze(1, 1, "test_questions.db");
        var room = maze.getRoom(0, 0);
        Item item = new ItemQuestionChange("", "", "");
        assertNull(room.getItem(), "Room not empty");
        assertTrue(room.addItem(item), "Successfully adding an item didn't return true");
        assertFalse(room.addItem(new ItemQuestionChange(" ", " ", " ")),
                "Failing to add item to full room didn't return  false");
        assertEquals(item, room.getItem(),
                "Item changed when adding to a room that already had an item");
    }

    @Test
    @DisplayName("Room: Take item when exists")
    void testTakeItemWhenExists() {
        var maze = new TriviaMaze(1, 1, "test_questions.db");
        var room = maze.getRoom(0, 0);
        assertNull(room.getItem(), "Room not empty");
        var item = new ItemQuestionChange("", "", "");
        assertTrue(room.addItem(item), "Successfully adding an item didn't return true");
        assertEquals(room.takeItem(), item, "Item taken different from item added");
        assertNull(room.takeItem(), "Room not empty after item taken");
    }

    @Test
    @DisplayName("Room: Take item when doesn't exist")
    void testTakeItemWhenNotExists() {
        var maze = new TriviaMaze(1, 1, "test_questions.db");
        var room = maze.getRoom(0, 0);
        assertNull(room.getItem(), "Room not empty");
        assertNull(room.takeItem(), "Empty room didn't return null");
        assertNull(room.getItem(), "Room not empty");
    }

    @Test
    @DisplayName("Room: Create doors, correct count in 5x5 maze")
    void testSetDoorsCount() {
        var maze = new TriviaMaze(5, 5, "test_questions.db");
        //5x5 maze should have 40 doors
        assertEquals(40, maze.getAllDoors().size());
    }

    @Test
    @DisplayName("Room: Create doors, correct count in 4x4 maze")
    void testSetDoorsCount2() {
        var maze = new TriviaMaze(4, 4, "test_questions.db");
        //4x4 maze should have 24 doors
        assertEquals(24, maze.getAllDoors().size());
    }

    @Test
    @DisplayName("Room: Create doors, no duplicates")
    void testSetDoorsAllUnique() {
        var maze = new TriviaMaze(5, 5, "test_questions.db");

        //Test for the test: uncommenting following line will add a duplicate room and fail test
        //maze.addDoor(maze.getRoom(1,1).getDoors().get(2));

        //Loop through all doors in maze and make sure all are unique
        HashSet<Door> doorSet = new HashSet<>();
        for(Door door : maze.getAllDoors()) {
            boolean contains = doorSet.contains(door);
            assertFalse(contains, "Duplicate door found");
            doorSet.add(door);
        }
    }
}