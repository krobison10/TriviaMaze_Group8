package model;

import java.util.ArrayList;
import java.util.List;

public class TriviaMaze {
    /**
     * Stores all the rooms that the maze contains.
     */
    private final Room[][] myRooms;
    /**
     * Stores all the doors that the maze contains for convenient
     * access to the entire set of doors in the maze. Stored in a
     * HashSet to simplify process of adding doors during maze creation
     * to avoid duplicates.
     */
    private final List<Door> myDoors;
    /**
     * Stores a reference to the player object that the maze contains.
     */
    private final Player myPlayer;
    /**
     * Represents the width and height of the maze in terms of rooms.
     * For example: "5 rooms wide, 5 rooms tall"
     */
    private final int myWidth, myHeight;

    public TriviaMaze(final int theWidth, final int theHeight) {
        myWidth = theWidth;
        myHeight = theHeight;
        myPlayer = new Player();
        myDoors = new ArrayList<>();

        myRooms = createRooms();
        initializeRooms();
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
        return result;
    }

    /**
     * @return a clone of the 2d array of all the rooms.
     */
    public Room[][] getAllRooms() {
        return myRooms;
    }

    /**
     * @return the Player.
     */
    public Player player() {
        return myPlayer;
    }

    /**
     * @return a list that contains all the doors in the entire maze.
     */
    public List<Door> getAllDoors() {
        return myDoors;
    }

    /**
     * @return the width of the maze.
     */
    public int getWidth() {
        return myWidth;
    }

    /**
     * @return the height of the maze.
     */
    public int getHeight() {
        return myHeight;
    }

    /**
     * Adds a door to the list which contains all the doors in the maze.
     * This method probably shouldn't be used outside the constructor
     * of Door.
     * @param theDoor the Door object to be added.
     */
    Door addDoor(final Door theDoor) {
        myDoors.add(theDoor);
        return theDoor;
    }

    /**
     * Creates new rooms for every position in the Room[][] array.
     * @return the initialized array.
     */
    private Room[][] createRooms() {
        var output = new Room[myHeight][myWidth];
        for(int i = 0; i < myHeight; i++) {
            for(int j = 0; j < myWidth; j++) {
                output[i][j] = new Room(j, i, this);
            }
        }
        return output;
    }

    /**
     * Initializes the doors for every room in myrooms by calling
     * the required method on them. This method cannot be called
     * before createRooms.
     */
    private Room[][] initializeRooms() {
        for(int i = 0; i < myHeight; i++) {
            for(int j = 0; j < myWidth; j++) {
                myRooms[i][j].initializeDoors(this);
            }
        }
        return myRooms;
    }
}
