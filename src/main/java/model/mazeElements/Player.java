package model.mazeElements;

import controller.KeyInput;
import view.TMPanel;

public class Player {


    private TMPanel setup;
    private KeyInput keys;
    // Player location on axis.
    private int playerLocationX, playerLocationY;
    // Player speed
    private int playerSpeed;

    /**
     * Player instantiation. Sets up TriviaMazUI, key listeners, and set player default values.
     * @param setup
     * @param keys
     */
    public Player(TMPanel setup, KeyInput keys) {
        this.setup = setup;
        this.keys = keys;

        setDefaultValues();
    }

    public Player() {}

    /**
     * PLayer default values.
     */
    public void setDefaultValues() {
        playerLocationX = playerLocationY = 3 * TMPanel.TILE_SIZE;
        playerSpeed = TMPanel.TILE_SIZE / 2;
    }

    /**
     * Reads key press input and updates player location accordingly.
     */
    public void updatePlayer() {
        if (keys.up || keys.down || keys.left || keys.right || keys.neutral) {
            if (keys.up) {
                setPlayerLocationY(-playerSpeed);
            } else if (keys.down) {
                setPlayerLocationY(playerSpeed);
            } else if (keys.left) {
                setPlayerLocationX(-playerSpeed);
            } else if (keys.right) {
                setPlayerLocationX(playerSpeed);
            }
        }
    }

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
}
