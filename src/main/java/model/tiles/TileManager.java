/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.tiles;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.mazeElements.Door;
import model.mazeElements.DoorStates;
import model.mazeElements.Room;
import model.mazeElements.TriviaMaze;
import view.TMPanel;

/**
 * Manages the tile system for the game.
 *
 * @author Kyler Robison
 */
public class TileManager {
    /**
     * Singleton instance.
     */
    private static TileManager instance;
    /**
     * Contains the types of tiles.
     */
    private final Tile[] myTiles;
    /**
     * Contains the numbers which represent types of tiles, read in from a file.
     */
    private int[][] myMapData;


    /**
     * Constructs and initializes the TileManager.
     */
    private TileManager() {
        instance = this;
        myTiles = new Tile[7];
        loadMap();
        loadTileSprites();
    }

    /**
     * @return a reference to the singleton instance.
     */
    public static TileManager getInstance() {
        if(instance == null) {
            instance = new TileManager();
        }
        return instance;
    }

    /**
     * Finds the tile position in the map data of a door given a room coordinate and door number.
     * Door numbers go from 0 to 3 clockwise starting with the east door ending with the south door.
     * @param roomX X coordinate number of room (0 based).
     * @param roomY Y coordinate number of room (0 based).
     * @param doorNum number of the door;
     * @return an int[] array with x coordinate in position 0 and y coordinate in position 1;
     */
    private static int[] findDoorTile(final int roomX, final int roomY, final int doorNum) {
        int roomCenterTileX = 3 + roomX * 6;
        int roomCenterTileY = 3 + roomY * 6;

        int[][] adjacency = {{-3, 0}, {0, -3}, {3, 0}, {0, 3}};

        int doorTileX = roomCenterTileX + adjacency[doorNum][0];
        int doorTileY = roomCenterTileY + adjacency[doorNum][1];

        return new int[] {doorTileX, doorTileY};
    }

    /**
     * @param theDoor door object.
     * @return tile position in the map of the door.
     * @throws RuntimeException if the door cannot be found.
     */
    public static int[] findTilePosOfDoor(final Door theDoor) {
        Room room;
        int bound = TriviaMaze.getInstance().getHeight();

        for(int i = 0; i < bound; i++) {
            for(int j = 0; j < bound; j++) {
                room = TriviaMaze.getInstance().getRoom(i, j);
                int doorNum = 0;
                for(Door d : room.getDoors()) {
                    if(theDoor == d) {
                        return findDoorTile(i, j, doorNum);
                    }
                    doorNum++;
                }

            }
        }
        throw new RuntimeException("Unable to find door");
    }

    public void updateDoorTile(final Door theDoor) {
        int[] coord = findTilePosOfDoor(theDoor);
        int x = coord[0];
        int y = coord[1];
        myMapData[y][x] = theDoor.getState() == DoorStates.OPENED ? 0 : 1;
    }

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
        new MapGenerator();
        try
        (FileReader fr = new FileReader("../TriviaMaze_Group8/src/main/resources/maps/randommap.txt")) {

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
     * Loads and stores sprites for each tile in the array.
     */
    private void loadTileSprites() {
        try {
            myTiles[0] = new Tile();//Empty tile for 0

            myTiles[1] = new Tile(ImageIO.read(Objects.requireNonNull
                    (getClass().getResourceAsStream("/tiles/wall.png"))), true);
            myTiles[2] = new Tile(ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/tiles/door.png"))), true);
            myTiles[3] = new Tile(ImageIO.read(Objects.requireNonNull
                    (getClass().getResourceAsStream("/tiles/ruler.png"))), false);
            myTiles[4] = new Tile(ImageIO.read(Objects.requireNonNull
                    (getClass().getResourceAsStream("/tiles/eraser.png"))), false);
            myTiles[5] = new Tile(ImageIO.read(Objects.requireNonNull
                    (getClass().getResourceAsStream("/tiles/pencil.png"))), false);
            myTiles[6] = new Tile(ImageIO.read(Objects.requireNonNull
                    (getClass().getResourceAsStream("/tiles/gold.png"))), false);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
