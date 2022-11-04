package model;

import java.util.ArrayList;

public class Room {
    /**
     * Represents the x and y position of the room within the maze
     */
    private int myPosX, myPosY;
    /**
     * Represents the item that the room may contain, will be null
     * if there aren't any items
     */
    private Item myItem;
    /**
     * Stores the doors that are connected to the particular room
     */
    private ArrayList<Door> myDoors;

    Room() {

    }

    public ArrayList<Door> getDoors() {
        return myDoors; //Need to update to return a copy that doesn't break encapsulation
    }

    public Item takeItem() {
        var temp = myItem;
        myItem = null;
        return temp;
    }

    public Item getItem() {
        return myItem;
    }

    public int getX() {
        return myPosX;
    }

    public int getY() {
        return myPosY;
    }
}
