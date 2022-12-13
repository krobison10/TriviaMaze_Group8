/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import java.awt.*;

import controller.PlayerController;
import model.mazeElements.TriviaMaze;
import model.tiles.Tiles;

/**
 * Draws graphics to the game panel.
 *
 * @author Kyler Robison
 */
public class GraphicDrawer {

    /**
     * Draws stationary tiles to the game board.
     * @param theG2 the graphics.
     */
    public static void drawTiles(final Graphics2D theG2) {
        int tileNum, bound = TMPanel.NUM_TILES, ts = TMPanel.TILE_SIZE;

        for(int col = 0; col < bound; col++)
        {
            for(int row = 0; row < bound; row++)
            {
                theG2.drawImage(TriviaMaze.getInstance().tileManager().getTile(Tiles.FLOOR.ordinal()).image(),
                        col * ts, row * ts, ts, ts, null);
                tileNum = TriviaMaze.getInstance().tileManager().getMapData()[row][col];
                theG2.drawImage(TriviaMaze.getInstance().tileManager().getTile(tileNum).image(),
                        col * ts, row * ts, ts, ts, null);
            }
        }
    }

    /**
     * Draws player using new location.
     * @param theG2 the graphics.
     */
    public static void drawPlayer(final Graphics2D theG2) {
        PlayerController.getInstance().drawMe(theG2, TriviaMaze.getInstance().player().locationX(),
                TriviaMaze.getInstance().player().locationY());
    }
}
