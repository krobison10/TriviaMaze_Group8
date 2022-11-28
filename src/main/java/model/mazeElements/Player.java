package model.mazeElements;

import view.TMPanel;

public class Player {

    /**
     * The coordinates of the player in the maze.
     */
    private int myLocationX, myLocationY;

    private final int coordinateMax = TMPanel.GAME_SIZE - (TMPanel.TILE_SIZE * 2);

    private final int coordinateMin = TMPanel.TILE_SIZE;
    /**
     * The speed of the player.
     */
    private int mySpeed;
    /**
     * Global point of access to instance of Player.
     */
    public static Player instance;

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
        if (getPlayerLocationX() + theChangeX <= coordinateMax && getPlayerLocationX() + theChangeX >= coordinateMin) {
            myLocationX += theChangeX;
        }
    }

    /**
     * Set player's location on Y axis (up/down movement).
     * Also ensures player image does not leave game window.
     * @param theChangeY the change in the Y value.
     */
    public void setLocationY(final int theChangeY) {
    if (getPlayerLocationY() + theChangeY <= coordinateMax && getPlayerLocationY() + theChangeY >= coordinateMin) {
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
