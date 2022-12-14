/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.mazeElements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model.items.Item;
import model.items.ItemDatabase;
import model.items.ItemInventory;
import model.questions.Question;
import model.questions.QuestionBank;
import model.tiles.TileManager;

/**
 * The main driver class for the model package. Represents the main maze and contains
 * all the rooms within. Upon construction, automatically initializes necessary objects
 * in model, and stores the data or references to the data in some way.
 *
 * @author Kyler Robison
 */
public class TriviaMaze implements Serializable {
    /**
     * Singleton instance.
     */
    private static TriviaMaze instance;
    /**
     * Represents the width and height of the maze in terms of rooms.
     * For example: "5 rooms wide, 5 rooms tall"
     */
    private final int myWidth, myHeight;
    /**
     * Stores all the rooms that the maze contains.
     */
    private final Room[][] myRooms;
    /**
     * Stores all the doors that the maze contains for convenient
     * access to the entire list of doors in the maze.
     */
    private final List<Door> myDoors;
    /**
     * Player in the maze.
     */
    private final Player myPlayer;
    /**
     * The questionBank object for the instance of the TriviaMaze. Builds and
     * contains all the Questions for the maze.
     */
    private final QuestionBank myQuestionBank;
    /**
     * The ItemInventory object for the instance of the TriviaMaze. Builds and
     * contains all the item for the inventory.
     */
    private final ItemInventory myInventory;
    /**
     * Reference to the tile manager, lives here for easy serialization
     */
    private final TileManager myTileManager;

    /**
     * Constructs and initializes the underlying implementation of the
     * trivia maze and any objects that it contains.
     * @param theWidth the width of the maze.
     * @param theHeight the height of the maze.
     * @param theDBName the filename of the db containing the questions.
     *                  Path not needed, finds the db file in the "databases"
     *                  directory.
     */
    public TriviaMaze(final int theWidth, final int theHeight, final String theDBName) {
        instance = this;
        if(theWidth < 2 || theHeight < 2) {
            throw new IllegalArgumentException("Invalid maze size");
        }
        myWidth = theWidth;
        myHeight = theHeight;
        myDoors = new ArrayList<>();
        myQuestionBank = new QuestionBank("../TriviaMaze_group8/databases/" + theDBName);
        myRooms = createRooms();
        myInventory = new ItemInventory();
        myPlayer = new Player();
        myTileManager = new TileManager();
        createInventory();
        initializeRooms();
    }

    /**
     * Throws exception if instance doesn't exist, arguments are required to create an instance.
     * @return the singleton instance.
     */
    public static TriviaMaze getInstance() {
        if(instance == null) {
            throw new RuntimeException("Attempted to get new instance of singleton without arguments");
        }
        return instance;
    }

    /**
     * Sets the singleton instance, only meant for use when deserializing data.
     * @param theInstance the new instance.
     */
    public static void setInstance(final TriviaMaze theInstance) {
        instance = theInstance;
    }

    /**
     * @param theX the X position from the room (0 based).
     * @param theY the Y position from the room (0 based).
     * @return the room at the requested location, or null if out of bounds.
     */
    public Room getRoom(final int theX, final int theY) {
        if(theX < 0 || theY < 0 || theX >= myWidth || theY >= myHeight) {
            return null;
        }
        return myRooms[theY][theX];
    }

    /**
     * @return a list that contains all the doors in the entire maze.
     */
    public List<Door> getAllDoors() {
        return myDoors;
    }

    /**
     * Searches for a door based on its question object.
     * @param theQuestion the question to search with.
     * @return a reference to the door object.
     * @throws NoSuchElementException if the door containing the given question is not found.
     */
    public Door getDoor(final Question theQuestion) {
        for(Door d : myDoors) {
            if(d.getQuestion() == theQuestion) {
                return d;
            }
        }
        throw new NoSuchElementException("Door with given question not found");
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
     * @return the Player object.
     */
    public Player player() {
        return myPlayer;
    }

    /**
     * @return an object containing the inventory items.
     */
    public ItemInventory inventory() {
        return myInventory;
    }

    /**
     * @return the tile manager.
     */
    public TileManager tileManager() {
        return myTileManager;
    }

    /**
     * @return True if there is a valid path to the exit of the maze
     * (bottommost, rightmost room). False otherwise.
     */
    public boolean existsPathToExit() {
        boolean[][] subProblems = new boolean[myHeight][myWidth];
        subProblems[0][0] = true;
        return pathExistsHelper(0, 0, subProblems);
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
     * @return the QuestionBank for the triviaMaze.
     */
    QuestionBank questionBank() {
        return myQuestionBank;
    }

    /**
     * Creates new rooms for every position in the Room[][] array.
     * @return the initialized array.
     */
    private Room[][] createRooms() {
        var output = new Room[myHeight][myWidth];
        for(int i = 0; i < myHeight; i++) {
            for(int j = 0; j < myWidth; j++) {
                output[i][j] = new Room(j, i);
            }
        }
        return output;
    }

    /**
     * Initializes the doors for every room in myRooms by calling
     * their initialize method. This method cannot be called
     * before createRooms is called.
     */
    private void initializeRooms() {
        for(int i = 0; i < myHeight; i++) {
            for(int j = 0; j < myWidth; j++) {
                myRooms[i][j].initializeDoors();
            }
        }
    }

    /**
     * Recursive algorithm to search for a valid path within the maze.
     * @param theY the Y position of the current room in the algorithm.
     * @param theX the X position of the current room in the algorithm.
     * @param theSubs a 2d array indicating whether there is a valid
     *                path to from the entrance to a corresponding room
     *                in the maze.
     * @return true if the current room is the final room, false if all
     * adjacent rooms aren't valid steps in a path to the exit.
     */
    private boolean pathExistsHelper(final int theY, final int theX, final boolean[][] theSubs) {

        //Base case, current room is the destination
        if(theX == myWidth - 1 && theY == myWidth - 1) {
            return true;
        }

        Room curRoom = getRoom(theX, theY);

        /* Stores the sets of values needed to be added to x and y to find x and y of
         * neighboring rooms in the order: south, east, north, then west. */
        int[][] adjacencies = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // doorDir stores the integer needed to receive the current door from the current room.
        int adjX, adjY, doorDir = DoorDirections.SOUTH.ordinal();
        for(int[] adj : adjacencies) {
            adjX = theX + adj[0];
            adjY = theY + adj[1];
            /* If room exists in the direction, and it doesn't already have a path to entrance,
             * and door isn't blocked, then traverse in that direction */
            if(getRoom(adjX, adjY) != null
                    && !theSubs[adjY][adjX]
                    && curRoom.getDoors().get(doorDir).getState() != DoorStates.BLOCKED) {

                /* Mark the matrix of sub problems to state that the room has a valid
                 * path to the entrance */
                theSubs[adjY][adjX] = true;

                /* Recursive call to current adjacent room, returns true if the resulting path
                 * from there is valid */
                if(pathExistsHelper(adjY, adjX, theSubs)) return true;

            }
            /* If current direction was a failure, recursion will unwind, decrement door direction
             * meaning that the algorithm will attempt to search in this next direction in the next
             * iteration. */
            doorDir--;
        }
        return false;
    }

    /**
     * Create a new inventory with random number of items
     *
     * @author Minh Le
     */
    private void createInventory() {
        int maxInventory = ItemInventory.MAX_INVENTORY;
        int maxPencil = maxInventory/2;
        int maxEraser = maxInventory/2;

        // Ensure the maximum number of items in the inventory within the limit
        // There are no more pencils or erasers than the limit.
        while(maxInventory > 0){
            Item newItem = new ItemDatabase().getRandomItem();
            if(newItem.getItemName().equalsIgnoreCase("pencil") && maxPencil > 0) {
                myInventory.addItem(newItem);
                maxPencil--;
                maxInventory--;
            } else if(newItem.getItemName().equalsIgnoreCase("eraser") && maxEraser > 0) {
                myInventory.addItem(newItem);
                maxEraser--;
                maxInventory--;
            }
        }
    }
}
