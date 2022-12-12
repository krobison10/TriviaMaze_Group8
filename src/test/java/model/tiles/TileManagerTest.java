package model.tiles;

import model.mazeElements.Door;
import model.mazeElements.DoorDirections;
import model.mazeElements.DoorStates;
import model.mazeElements.TriviaMaze;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TileManagerTest {

    @Test
    void updateDoorTile() {
        TriviaMaze maze = new TriviaMaze(5, 5, "cs_trivia_questions.db");
        Door d = maze.getRoom(0,0).getDoors().get(DoorDirections.EAST.ordinal());
        assertEquals(Tiles.DOOR.ordinal(), maze.tileManager().getMapData()[3][6],
                "Door needed to be closed");
        d.setState(DoorStates.BLOCKED);
        maze.tileManager().updateDoorTile(d);
        assertEquals(Tiles.DOOR_BLOCKED.ordinal(),  maze.tileManager().getMapData()[3][6],
                "Door needed to be blocked");
    }
}