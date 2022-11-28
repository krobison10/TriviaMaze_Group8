package model.tiles;

import view.TMPanel;

import javax.imageio.ImageIO;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    /**
     * Contains the types of tiles.
     */
    private final Tile[] myTiles;
    /**
     * Contains the numbers which represent types of tiles, read in from a file.
     */
    private int[][] myMapData;

    public static TileManager instance;

    /**
     * Constructs and initializes the TileManager.
     */
    public TileManager() {
        instance = this;
        myTiles = new Tile[3];
        loadMap();
        getTileImages();
    }

    /**
     * @return the array containing the map data, contains data for which tile number goes where
     * on the game board.
     */
    public int[][] getMapData() {
        return myMapData;
    }

    /**
     * Gets a tile at a given location in the array of tiles.
     * @param theIndex the index of the tile in the array.
     * @return the Tile object at that position.
     */
    public Tile getTile(final int theIndex) {
        return myTiles[theIndex];
    }

    /**
     * Loads the map data in from a file and stores it in the array "myMapData".
     */
    private void loadMap() {
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

    /**
     * Loads the tile images into the array "myTiles".
     */
    private void getTileImages() {
        try {
            myTiles[1] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))), true);
            myTiles[2] = new Tile(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/tiles/door.png"))), true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
