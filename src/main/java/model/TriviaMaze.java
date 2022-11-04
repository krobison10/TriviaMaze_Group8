package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TriviaMaze {
    /**
     * Stores all the rooms that the maze contains.
     */
    private Room[][] myRooms;
    /**
     * Stores all the doors that the maze contains for convenient
     * access to the entire set of doors in the maze. Stored in a
     * HashSet to simplify process of adding doors during maze creation
     * to avoid duplicates.
     */
    private HashSet<Door> myDoors;
    /**
     * Stores a reference to the player object that the maze contains.
     */
    private Player myPlayer;
    /**
     * Represents the width and height of the maze in terms of rooms.
     * For example: "5 rooms wide, 5 rooms tall"
     */
    private int myWidth, myHeight;

    public TriviaMaze(final int theWidth, final int theHeight) {

    }

    public Room getRoom(final int theXPos, final int theYPos) {
        return null;
    }

    public ArrayList<Room> getAllRooms() {
        return (ArrayList<Room>) Arrays.asList(myRooms);
    }

    public Player player() {
        return myPlayer;
    }

    public HashSet<Door> getAllDoors() {
        return myDoors;
    }

    void addDoor(final Door theDoor) {

    }
}
