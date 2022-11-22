package controller;

import model.mazeElements.Player;
import view.TMPanel;

public class PlayerController {

    private final Player myPlayer;

    private final KeyInput keys;

    public PlayerController(final Player thePlayer, final KeyInput theKeys) {
        myPlayer = thePlayer;
        keys = theKeys;
        initPlayer();
    }

    /**
     * Reads key press input and updates player location accordingly.
     */
    public void updatePlayer() {
        if (keys.up || keys.down || keys.left || keys.right || keys.neutral) {
            if (keys.up) {
                myPlayer.setPlayerLocationY(-myPlayer.getSpeed());
            } else if (keys.down) {
                myPlayer.setPlayerLocationY(myPlayer.getSpeed());
            } else if (keys.left) {
                myPlayer.setPlayerLocationX(-myPlayer.getSpeed());
            } else if (keys.right) {
                myPlayer.setPlayerLocationX(myPlayer.getSpeed());
            }
        }
    }

    /**
     * PLayer default values.
     */
    public void initPlayer() {
        myPlayer.setPlayerLocationY(3 * TMPanel.TILE_SIZE);
        myPlayer.setPlayerLocationX(3 * TMPanel.TILE_SIZE);
        myPlayer.setSpeed(TMPanel.TILE_SIZE / 2);
    }
}
