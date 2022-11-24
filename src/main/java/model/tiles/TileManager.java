package model.tiles;

import view.TMPanel;

import javax.imageio.ImageIO;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    public static Tile[] myTiles;
    public static int[][] myMapData;

    public TileManager() {
        myTiles = new Tile[3];
        loadMap();
        getTileImages();
    }

    public void getTileImages() {
        try {
            myTiles[0] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))));
            myTiles[0].setCollidable(false);
            myTiles[1] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))));
            myTiles[1].setCollidable(true);

            myTiles[2] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/door.png"))));
            myTiles[2].setCollidable(true);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[][] getMapData() {
        return myMapData;
    }

    public Tile getTile(final int theIndex) {
        return myTiles[theIndex];
    }

    public void loadMap() {
        try (FileReader fr = new FileReader("../TriviaMaze_Group8/src/main/resources/maps/map2.txt")) {
            int nt = TMPanel.NUM_TILES;
            myMapData = new int[nt][nt];

            for(int i = 0; i < nt+1; i++) {
                for(int j = 0; j < nt+1; j++) {
                    char s = (char) fr.read();
                    if(s == ' ') s = '0';
                    if(s != '\n' && s != '\uFFFF') {
                        myMapData[i][j] = Integer.parseInt(String.valueOf(s));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
