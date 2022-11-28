package model.mazeElements;

import view.TMPanel;

public class Player {

    /**
     * The coordinates of the player in the maze.
     */
    private int myLocationX, myLocationY;

    /**
     * The speed of the player.
     */
    private int mySpeed;

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
    public void setPlayerLocationX(final int theChangeX) {
        int max = TMPanel.GAME_SIZE - TMPanel.TILE_SIZE;
        if (getPlayerLocationX() + theChangeX <= max && getPlayerLocationX() + theChangeX >= 0) {
            myLocationX += theChangeX;
        }
    }

    /**
     * Set player's location on Y axis (up/down movement).
     * Also ensures player image does not leave game window.
     * @param theChangeY the change in the Y value.
     */
    public void setPlayerLocationY(final int theChangeY) {
        int max = TMPanel.GAME_SIZE - TMPanel.TILE_SIZE;
        if (getPlayerLocationY() + theChangeY <= max && getPlayerLocationY() + theChangeY >= 0) {
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
