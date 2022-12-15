/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.tiles;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Represents a tile on the game board.
 *
 * @author Kyler Robison
 */
public class Tile implements Serializable {
    /**
     * The image for the tile.
     */
    private final BufferedImage myImage;
    /**
     * Whether the tile is collidable or not.
     */
    private final boolean myCollidable;

    /**
     * Constructs a new Tile.
     * @param theImage the image for the tile, should be square.
     * @param isCollidable whether the tile will be collidable or not.
     */
    public Tile(final BufferedImage theImage, final boolean isCollidable) {
        if(theImage == null) {
            throw new IllegalArgumentException("Tile: image cannot be null");
        }
        myCollidable = isCollidable;
        myImage = theImage;
    }

    /**
     * @return the image of the Tile.
     */
    public BufferedImage image() {
        return myImage;
    }

    /**
     * @return true if the player should collide with the tile, false otherwise.
     */
    public boolean isCollidable() {
        return myCollidable;
    }
}
