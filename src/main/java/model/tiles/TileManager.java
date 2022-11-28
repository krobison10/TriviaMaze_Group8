package model.tiles;

import view.MapGenerator;
import view.TMPanel;

import javax.imageio.ImageIO;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class TileManager {
    Tile[] myTiles;
    int[][] myMapData;

    public TileManager() {
        myTiles = new Tile[7];
        loadMap();
        getTileImages();
    }

    public void getTileImages() {
        try {
            myTiles[1] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))));
            myTiles[2] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/door.png"))));
            myTiles[3] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/ruler.png"))));
            myTiles[4] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/eraser.png"))));
            myTiles[5] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pencil.png"))));
            myTiles[6] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/gold.png"))));
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
        new MapGenerator();
        try (FileReader fr = new FileReader("../TriviaMaze_Group8/src/main/resources/maps/randommap.txt")) {
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
