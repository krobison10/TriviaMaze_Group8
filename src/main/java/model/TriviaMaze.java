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

    /**
     * @param theX the X position from the room (0 based).
     * @param theY the Y position from the room (0 based).
     * @return the Room in the location specified, null if the room is
     * not in bounds.
     */
    public Room getRoom(final int theX, final int theY) {
        Room result = null;
        if(theX >= 0 && theY >= 0 && theX < myWidth && theY < myHeight) {
            result = myRooms[theY][theX];
        }
//        else {
//            throw new IndexOutOfBoundsException("Position given is out of range");
//        }
        return result;
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

    public int getWidth() {
        return myWidth;
    }

    public int getHeight() {
        return myHeight;
    }

    void addDoor(final Door theDoor) {

    }
}
