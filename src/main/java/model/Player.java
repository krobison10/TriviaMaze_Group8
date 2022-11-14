package model;

import controller.KeyInput;
import view.TriviaMazeUI;
import java.awt.*;

public class Player {


    TriviaMazeUI setup;
    KeyInput keys;
    // Player location on axis.
    private int playerLocationX, playerLocationY;
    // Player size
    private final int playerSize = 50;
    // Player speed
    private int playerSpeed;

    /**
     * Player instantiation. Sets up TriviaMazUI, key listeners, and set player default values.
     * @param setup
     * @param keys
     */
    public Player(TriviaMazeUI setup, KeyInput keys) {
        this.setup = setup;
        this.keys = keys;

        setDefaultValues();
    }

    /**
     * PLayer default values.
     */
    public void setDefaultValues() {
        playerLocationX = 0;
        playerLocationY = 0;
        playerSpeed = 20;
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
     * Draws the player.
     * @param g2
     */
    public void draw(Graphics2D g2) {

        // temp player until graphics are decided
        g2.setColor(Color.white);
        g2.fillRect(getPlayerLocationX(),getPlayerLocationY(),playerSize,playerSize);
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
        if (getPlayerLocationX() + changeX <= 500 && getPlayerLocationX() + changeX >= 0) {
            playerLocationX += changeX;
        }
    }

    /**
     * Set player's location on Y axis (up/down movement).
     * Also ensures player image does not leave game window.
     * @param changeY
     */
    public void setPlayerLocationY(int changeY) {
        if (getPlayerLocationY() + changeY <= 500 && getPlayerLocationY() + changeY >= 0) {
            playerLocationY += changeY;
        }
    }
}
