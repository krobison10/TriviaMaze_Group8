/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.mazeElements;

import view.TMPanel;

/**
 * Represents state and basic behavior of the player in game.
 *
 * @author Kyler Robison & AJ Garcia
 */
public class Player {

    /**
     * Max coordinate of player for bound if collision fails.
     */
    private static final int COORDINATE_MAX = TMPanel.GAME_SIZE - (TMPanel.TILE_SIZE * 2);
    /**
     * Min coordinate of player for bound if collision fails.
     */
    private static final int COORDINATE_MIN = TMPanel.TILE_SIZE;
    /**
     * Global point of access to singleton instance of Player.
     */
    public static Player instance;
    /**
     * The coordinates of the player in the maze.
     */
    private int myLocationX, myLocationY;
    /**
     * The speed of the player.
     */
    private int mySpeed;


    /**
     * Constructs a player object.
     */
    public Player() {
        instance = this;
    }

    /**
     * @return the player's location on the X axis.
     */
    public int getPlayerLocationX() {
        return myLocationX;
    }

    /**
     * @return the player's location on the Y axis.
     */
    public int getPlayerLocationY() {
        return myLocationY;
    }

    /**
     * Sets player's location on X axis (left/right movement).
     * Also ensures player image does not leave game window.
     * @param theChangeX the change in the X value.
     */
    public void setLocationX(final int theChangeX) {
        if (getPlayerLocationX() + theChangeX <= COORDINATE_MAX
                && getPlayerLocationX() + theChangeX >= COORDINATE_MIN) {
            myLocationX += theChangeX;
        }
    }

    /**
     * Set player's location on Y axis (up/down movement).
     * Also ensures player image does not leave game window.
     * @param theChangeY the change in the Y value.
     */
    public void setLocationY(final int theChangeY) {
        if (getPlayerLocationY() + theChangeY <= COORDINATE_MAX
            && getPlayerLocationY() + theChangeY >= COORDINATE_MIN) {
            myLocationY += theChangeY;
        }
    }

    /**
     * @return the speed value of the player object.
     */
    public int getSpeed() {
        return mySpeed;
    }

    /**
     * Sets the speed value of the player.
     * @param theSpeed the new speed.
     */
    public void setSpeed(final int theSpeed) {
        mySpeed = theSpeed;
    }
}
