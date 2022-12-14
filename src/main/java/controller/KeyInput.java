/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * CLASS DESCRIPTION HERE
 *
 * @author AJ Garcia
 */
public class KeyInput implements KeyListener {

    /**
     * Directions for the player.
     */
    private boolean myUp, myDown, myLeft, myRight, myNeutral, myCheat;


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
        myNeutral = false;

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            myUp = true;
        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            myLeft = true;
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            myDown = true;
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            myRight = true;
        }
        if (key == KeyEvent.VK_0) {
            myCheat = true;
        }
    }

    /**
     * Reads key releases from user.
     * @param e the event to be processed.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        myNeutral = true;

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            myUp = false;
        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            myLeft = false;
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            myDown = false;
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            myRight = false;
        }
        if (key == KeyEvent.VK_0) {
            myCheat = false;
        }
    }

    /**
     * @return true if the player direction is up.
     */
    public boolean getMyUp() {
        return myUp;
    }

    /**
     * @return true if the player direction is down.
     */
    public boolean getMyDown() {
        return myDown;
    }

    /**
     * @return true if the player direction is left.
     */
    public boolean getMyLeft() {
        return myLeft;
    }

    /**
     * @return true if the player direction is right.
     */
    public boolean getMyRight() {
        return myRight;
    }

    /**
     * @return true if the player direction is neutral.
     */
    public boolean getMyNeutral() {
        return myNeutral;
    }

    /**
     *
     * @return true if player wants to cheat by going through walls.
     */
    public boolean getMyCheat() {
        return myCheat;
    }

    /**
     * Sets the neutral state of the KeyInput.
     * @param theNeutral the new state.
     */
    public void setMyNeutral(final boolean theNeutral) {
        myNeutral = theNeutral;
    }
}
