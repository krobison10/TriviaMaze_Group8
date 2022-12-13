package model.mazeElements;

import org.junit.jupiter.api.*;
import view.TMPanel;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private static Player player;

    @BeforeAll
    static void setUp() {
        new TriviaMaze(5, 5, "CS_trivia_questions.db");
        player = TriviaMaze.getInstance().player();
    }

    @Test
    @DisplayName("Player: get location X")
    void locationX() {
        assertEquals(TMPanel.TILE_SIZE * 3, player.getMyLocationX(), "start location X incorrect");
    }

    @Test
    @DisplayName("Player: get location Y")
    void locationY() {
        assertEquals(TMPanel.TILE_SIZE * 3, player.getMyLocationY(), "start location Y incorrect");
    }

    @Test
    @DisplayName("Player: change X location")
    void changeX() {
        int old = player.getMyLocationX();
        player.setMyLocationX(TMPanel.TILE_SIZE);
        assertEquals(old + TMPanel.TILE_SIZE, player.getMyLocationX());
    }

    @Test
    @DisplayName("Player: change X location")
    void changeY() {
        int old = player.getMyLocationY();
        player.setMyLocationY(TMPanel.TILE_SIZE);
        assertEquals(old + TMPanel.TILE_SIZE, player.getMyLocationY());
    }

    @Test
    void getSpeed() {
        assertEquals(TMPanel.TILE_SIZE, player.getMySpeed(), "unexpected speed value");
    }

    @Test
    void getCurrentRoom() {
        Room expected = TriviaMaze.getInstance().getRoom(0, 0);
        player.setMyCurrentRoom(expected);
        assertEquals(expected, player.getMyCurrentRoom());
    }

    @Test
    void setCurrentRoom() {
        Room room = TriviaMaze.getInstance().getRoom(1, 1);
        player.setMyCurrentRoom(room);
        assertEquals(room, player.getMyCurrentRoom(), "wrong room set");
    }
}