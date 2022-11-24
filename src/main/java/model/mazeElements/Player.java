package model.mazeElements;

import view.TMPanel;

public class Player {

    // Player location on axis.
    private int playerLocationX, playerLocationY;
    // Player speed
    private int speed;

    /**
     * Returns player's location on X axis.
     * @return
     */
    public int getPlayerLocationX() {
        return playerLocationX;
    }

    /**
     * Returns player's location on Y axis.
     * @return
     */
    public int getPlayerLocationY() {
        return playerLocationY;
    }

    /**
     * Sets player's location on X axis (left/right movement).
     * Also ensures player image does not leave game window.
     * @param changeX
     */
    public void setPlayerLocationX(int changeX) {
        int max = TMPanel.GAME_SIZE - TMPanel.TILE_SIZE;
        if (getPlayerLocationX() + changeX <= max && getPlayerLocationX() + changeX >= 0) {
            playerLocationX += changeX;
        }
    }

    /**
     * Set player's location on Y axis (up/down movement).
     * Also ensures player image does not leave game window.
     * @param changeY
     */
    public void setPlayerLocationY(int changeY) {
        int max = TMPanel.GAME_SIZE - TMPanel.TILE_SIZE;
        if (getPlayerLocationY() + changeY <= max && getPlayerLocationY() + changeY >= 0) {
            playerLocationY += changeY;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
