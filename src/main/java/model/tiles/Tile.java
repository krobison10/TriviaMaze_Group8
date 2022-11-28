package model.tiles;

import java.awt.image.BufferedImage;

public class Tile {
    /**
     * The image for the tile.
     */
    private final BufferedImage myImage;
    /**
     * Whether the tile is collidable or not.
     */
    private final boolean collidable;

    /**
     * Constructs a new Tile.
     * @param theImage the image for the tile, should be square.
     * @param isCollidable whether the tile will be collidable or not.
     */
    public Tile(final BufferedImage theImage, final boolean isCollidable) {
        collidable = isCollidable;
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
        return collidable;
    }
}
