/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import java.awt.*;

import controller.PlayerController;
import model.mazeElements.Player;
import model.mazeElements.TriviaMaze;

/**
 * Draws graphics to the game panel.
 *
 * @author Kyler Robison
 */
public class GraphicDrawer {
    /**
     * Singleton instance.
     */
    public static GraphicDrawer instance;


    private GraphicDrawer() {}

    /**
     * @return the singleton instance. Creates a new one if one does not yet exist.
     */
    public static GraphicDrawer getInstance() {
        if(instance == null) {
            instance = new GraphicDrawer();
        }
        return instance;
    }

    /**
     * Resets the instance by setting the field to null.
     * Next time getInstance() is called, a new instance will be created.
     */
    public static void resetInstance() {
        instance = null;
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
                tileNum = TriviaMaze.getInstance().tileManager().getMapData()[row][col];
                if(tileNum != 0) {
                    theG2.drawImage(TriviaMaze.getInstance().tileManager().getTile(tileNum).image(),
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
        PlayerController.getInstance().drawMe
                (theG2, Player.getInstance().getPlayerLocationX(), Player.getInstance().getPlayerLocationY());
    }
}
