package view;

import model.tiles.TileManager;
import model.mazeElements.Player;

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

    void drawPlayer(final Graphics2D theG2) {
        theG2.setColor(Color.white);
        int size = TMPanel.TILE_SIZE;
        theG2.fillRect(myPlayer.getPlayerLocationX(),myPlayer.getPlayerLocationY(), size, size);
    }

}
