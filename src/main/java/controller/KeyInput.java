/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author AJ Garcia
 */
public class KeyInput implements KeyListener {

    /**
     * Directions for the player.
     */
    private boolean up, down, left, right, neutral;


    /**
     * Not used.
     * @param e the event to be processed.
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Reads key presses from user.
     * @param e the event to be processed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        neutral = false;

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            up = true;
        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            left = true;
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            down = true;
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    /**
     * Reads key releases from user.
     * @param e the event to be processed.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        neutral = true;

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            up = false;
        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            left = false;
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            down = false;
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    /**
     * @return true if the player direction is up.
     */
    public boolean up() {
        return up;
    }

    /**
     * @return true if the player direction is down.
     */
    public boolean down() {
        return down;
    }

    /**
     * @return true if the player direction is left.
     */
    public boolean left() {
        return left;
    }

    /**
     * @return true if the player direction is right.
     */
    public boolean right() {
        return right;
    }

    /**
     * @return true if the player direction is neutral.
     */
    public boolean neutral() {
        return neutral;
    }

    /**
     * Sets the neutral state of the KeyInput.
     * @param theNeutral the new state.
     */
    public void setNeutral(final boolean theNeutral) {
        neutral = theNeutral;
    }
}
