package view;

import controller.PlayerController;
import model.mazeElements.Player;
import model.tiles.TileManager;

import java.awt.*;

class GraphicDrawer {
    private final TileManager myTM;
    private final Player myPlayer;

    GraphicDrawer(final Player thePlayer) {
        myTM = new TileManager();
        myPlayer = thePlayer;

    }

    void drawTiles(final Graphics2D theG2) {
        int tileNum, bound = TMPanel.NUM_TILES, ts = TMPanel.TILE_SIZE;

        for(int col = 0; col < bound; col++)
        {
            for(int row = 0; row < bound; row++)
            {
                tileNum = myTM.getMapData()[row][col];
                if(tileNum != 0) {
                    theG2.drawImage(myTM.getTile(tileNum).image(), col * ts, row * ts, ts, ts, null);
                }
            }
        }
    }

    /**
     * Draws player using new location
     * @param theG2
     */
    void drawPlayer(final Graphics2D theG2) {
        PlayerController.drawMe(theG2, Player.getPlayerLocationX(), Player.getPlayerLocationY());
    }

}
