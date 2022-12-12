package model.tiles;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    @DisplayName("Tile: get image")
    void test1() {
        BufferedImage img = new BufferedImage(1,1, 1);
        Tile t = new Tile(img, true);
        assertEquals(img, t.image(), "Wrong image returned");
    }

    @Test
    @DisplayName("Tile: construct with null image")
    void test2() {
        assertThrows(IllegalArgumentException.class, () -> new Tile(null, true),
                "Expected exception but none were thrown");
    }

    @Test
    @DisplayName("Tile: is collidable")
    void test3() {
        BufferedImage img = new BufferedImage(1,1, 1);
        Tile t = new Tile(img, true);
        assertTrue(t.isCollidable());
        t = new Tile(img, false);
        assertFalse(t.isCollidable());
    }


}