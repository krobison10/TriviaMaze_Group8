package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a room in the maze. Contains functionality to initialize itself, specifically about
 * creating doors correctly and handling sharing doors with adjacent rooms. Stores data about its
 * doors and also the item that the room may contain.
 */
public class Room {
    /**
     * Represents the position of the room within the maze
     */
    private final int myPosX, myPosY;
    /**
     * Represents the item that the room may contain, will be null
     * if there aren't any items
     */
    private Item myItem;
    /**
     * Stores the doors that are connected to the particular room
     * in a list of length 4.
     * Doors are stored in the array in the order: West, North,
     * East, then South. If a door doesn't exist in a direction,
     * then the reference will be null.
     */
    private List<Door> myDoors;
    /**
     * Reference to the containing maze.
     */
    private final TriviaMaze myMaze;

    /**
     * Constructs a room object and handles necessary operations such
     * as creation
     * @param theX represents the X position in the maze.
     * @param theY represents the Y position in the maze.
     * @param theMaze a reference to the TriviaMaze that contains
     *                the room, so that rooms can be aware of other
     *                rooms and doors during construction of a room.
     */
    Room(final int theX, final int theY, final TriviaMaze theMaze) {
        if(theX < 0 || theY < 0 || theX >= theMaze.getWidth() || theY >= theMaze.getHeight()) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        myPosX = theX;
        myPosY = theY;

        myMaze = theMaze;

        //Makes sure that myDoors isn't null to avoid null references when initializeDoors is called
        Door[] temp = {null, null, null, null};
        myDoors = new ArrayList<>(Arrays.asList(temp));
    }

    /**
     * @return a List of the doors that the room has
     */
    public List<Door> getDoors() {
        return myDoors; //Should update to return a copy that doesn't break encapsulation
    }

    /**
     * @return the Item that the room contains.
     */
    public Item getItem() {
        return myItem;
    }

    /**
     * @return the X position of the room within the maze.
     */
    public int getX() {
        return myPosX;
    }

    /**
     * @return the Y position of the room within the maze.
     */
    public int getY() {
        return myPosY;
    }

    /**
     * @return a String representation of the room for testing purposes.
     */
    @Override
    @Deprecated
    public String toString() {
        return "Room: X=" + myPosX + ", Y=" + myPosY + ", Item: " + myItem + "\nDoors: " + myDoors;
    }

    /**
     *
     * @param theItem the Item to be added to the room.
     * @return true if the item was added successfully, false if the room already contained an item.
     * In the false case, the existing item will be kept and the item to be added will be ignored.
     */
    boolean addItem(final Item theItem) {
        boolean successful = false;
        if(myItem == null) {
            myItem = theItem;
            successful = true;
        }
        return successful;
    }

    /**
     * Removes the item from the room.
     * @return the removed Item.
     */
    Item takeItem() {
        var temp = myItem;
        myItem = null;
        return temp;
    }

    /**
     * Calls setDoors to initialize the doors for the room.
     */
    void initializeDoors() {
        myDoors = setDoors();
    }

    /**
     * Loops through all the directions, for each direction, if in bounds
     * it either gets a reference to a door object from the adjacent room
     * to use for this room, or creates a new one if there isn't one yet.
     * @return a List of doors for the room.
     */
    private List<Door> setDoors() {
        Door[] init = {null, null, null, null};
        List<Door> result = Arrays.asList(init);
        if(myPosX - 1 >= 0) {
            //Get door from room to the west that current room would share
            var adjacent = myMaze.getRoom(myPosX - 1, myPosY).getDoors().get(2);
            result.set(0, (adjacent == null ? myMaze.addDoor(new Door(myMaze)) : adjacent));
        }
        if(myPosY - 1 >= 0) {
            //Get door from room to the north that current room would share
            var adjacent = myMaze.getRoom(myPosX, myPosY - 1).getDoors().get(3);
            result.set(1, (adjacent == null ? myMaze.addDoor(new Door(myMaze)) : adjacent));
        }
        if(myPosX + 1 < myMaze.getWidth()) {
            //Get door from room to the east that current room would share
            var adjacent = myMaze.getRoom(myPosX + 1, myPosY).getDoors().get(0);
            result.set(2, (adjacent == null ? myMaze.addDoor(new Door(myMaze)) : adjacent));
        }
        if(myPosY + 1 < myMaze.getHeight()) {
            //Get door from room to the north that current room would share
            var adjacent = myMaze.getRoom(myPosX, myPosY + 1).getDoors().get(1);
            result.set(3, (adjacent == null ? myMaze.addDoor(new Door(myMaze)) : adjacent));
        }
        return result;
    }
}
