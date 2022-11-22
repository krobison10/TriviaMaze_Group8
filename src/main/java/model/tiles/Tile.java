package model.tiles;

import java.awt.image.BufferedImage;

public class Tile {
    public  BufferedImage myImage;
    private boolean collidable;

    public Tile(final BufferedImage theImage) {
        this();
        myImage = theImage;
    }

    public Tile() {
        collidable = false;
    }

    public BufferedImage image() {
        return myImage;
    }

    public void setImage(final BufferedImage theImage) {
        myImage = theImage;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(final boolean collide) {
        collidable = collide;
    }
}
