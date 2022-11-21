package model.mazeElements;

import controller.KeyInput;
import view.TriviaMazeUI;
import java.awt.*;

public class Player {


    TriviaMazeUI setup;
    KeyInput keys;
    // Player location on axis.
    private int playerLocationX, playerLocationY;
    // Player size
    private final int playerSize = TriviaMazeUI.TILE_SIZE;
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

    public Player() {}

    /**
     * PLayer default values.
     */
    public void setDefaultValues() {
        playerLocationX = playerLocationY = 3 * TriviaMazeUI.TILE_SIZE;
        playerSpeed = TriviaMazeUI.TILE_SIZE / 2;
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
        int max = TriviaMazeUI.GAME_WIDTH - TriviaMazeUI.TILE_SIZE;
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
        int max = TriviaMazeUI.GAME_WIDTH - TriviaMazeUI.TILE_SIZE;
        if (getPlayerLocationY() + changeY <= max && getPlayerLocationY() + changeY >= 0) {
            playerLocationY += changeY;
        }
    }
}
