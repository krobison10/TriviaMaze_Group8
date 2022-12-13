/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.mazeElements;

import java.io.Serializable;

import view.TMPanel;

/**
 * Represents state and basic behavior of the player in game.
 *
 * @author Kyler Robison & AJ Garcia
 */
public class Player implements Serializable {

    /**
     * Max coordinate of player for bound if collision fails.
     */
    private static final int COORDINATE_MAX = TMPanel.GAME_SIZE - (TMPanel.TILE_SIZE * 2);
    /**
     * Min coordinate of player for bound if collision fails.
     */
    private static final int COORDINATE_MIN = TMPanel.TILE_SIZE;
    /**
     * The coordinates of the player in the maze.
     */
    private int myLocationX, myLocationY;
    /**
     * Reference to the current room that the player is in.
     */
    private Room myCurrentRoom;
    /**
     * The speed of the player.
     */
    private final int mySpeed;


    /**
     * Constructs a player object.
     */
    Player() {
        myCurrentRoom = TriviaMaze.getInstance().getRoom(0,0);
        myLocationX = myLocationY = TMPanel.TILE_SIZE * 3;
        mySpeed = TMPanel.TILE_SIZE;
    }

    /**
     * @return the player's location on the X axis.
     */
    public int getMyLocationX() {
        return myLocationX;
    }

    /**
     * @return the player's location on the Y axis.
     */
    public int getMyLocationY() {
        return myLocationY;
    }

    /**
     * Sets player's location on X axis (left/right movement).
     * Also ensures player image does not leave game window.
     * @param theChangeX the change in the X value.
     */
    public void setMyLocationX(final int theChangeX) {
        if (getMyLocationX() + theChangeX <= COORDINATE_MAX
                && getMyLocationX() + theChangeX >= COORDINATE_MIN) {
            myLocationX += theChangeX;
        }
    }

    /**
     * Set player's location on Y axis (up/down movement).
     * Also ensures player image does not leave game window.
     * @param theChangeY the change in the Y value.
     */
    public void setMyLocationY(final int theChangeY) {
        if (getMyLocationY() + theChangeY <= COORDINATE_MAX
            && getMyLocationY() + theChangeY >= COORDINATE_MIN) {
            myLocationY += theChangeY;
        }
    }

    /**
     * @return the speed value of the player object.
     */
    public int getMySpeed() {
        return mySpeed;
    }

    /**
     * @return a reference to the room that contains the player.
     */
    public Room getMyCurrentRoom() {
        return myCurrentRoom;
    }

    /**
     * Updates the current room of the player
     * @param theCurrentRoom the new room.
     */
    public void setMyCurrentRoom(final Room theCurrentRoom) {
        myCurrentRoom = theCurrentRoom;
    }
}
