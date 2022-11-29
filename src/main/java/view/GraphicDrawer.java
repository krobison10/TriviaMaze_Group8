/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import java.awt.*;

import controller.PlayerController;
import model.mazeElements.Player;
import model.tiles.TileManager;

/**
 * Draws graphics to the game panel.
 *
 * @author Kyler Robison
 */
class GraphicDrawer {
    /**
     * Singleton instance.
     */
    public static GraphicDrawer instance;

    /**
     * Initializes singleton instance.
     */
    GraphicDrawer() {
        instance = this;
    }

    /**
     * Draws stationary tiles to the game board.
     * @param theG2 the graphics.
     */
    void drawTiles(final Graphics2D theG2) {
        int tileNum, bound = TMPanel.NUM_TILES, ts = TMPanel.TILE_SIZE;

        for(int col = 0; col < bound; col++)
        {
            for(int row = 0; row < bound; row++)
            {
                tileNum = TileManager.instance.getMapData()[row][col];
                if(tileNum != 0) {
                    theG2.drawImage(TileManager.instance.getTile(tileNum).image(),
                            col * ts, row * ts, ts, ts, null);
                }
            }
        }
    }

    /**
     * Draws player using new location.
     * @param theG2 the graphics.
     */
    void drawPlayer(final Graphics2D theG2) {
        //Possible pro-MVC refactor, move the drawme function into this class
        PlayerController.instance.drawMe
                (theG2, Player.instance.getPlayerLocationX(), Player.instance.getPlayerLocationY());
    }
}
