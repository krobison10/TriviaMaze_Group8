package model.mazeElements;

import view.TMPanel;

public class Player {

    // Player location on axis.
    private static int playerLocationX, playerLocationY;
    // Player speed
    private int speed;
    private final int coordinateMax = TMPanel.GAME_SIZE - (TMPanel.TILE_SIZE * 2);
    private final int coordinateMin = TMPanel.TILE_SIZE;

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
        if (getPlayerLocationX() + changeX <= coordinateMax && getPlayerLocationX() + changeX >= coordinateMin) {
            playerLocationX += changeX;
        }
    }

    /**
     * Set player's location on Y axis (up/down movement).
     * Also ensures player image does not leave game window.
     * @param changeY
     */
    public void setPlayerLocationY(int changeY) {
        if (getPlayerLocationY() + changeY <= coordinateMax && getPlayerLocationY() + changeY >= coordinateMin) {
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
