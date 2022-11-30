/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.mazeElements.Player;
import model.mazeElements.Room;
import model.mazeElements.TriviaMaze;
import model.tiles.TileManager;
import view.TMPanel;

/**
 * Handles game logic concerning the animation and movement of the player.
 *
 * @author Kyler Robison & AJ Garcia
 */
public class PlayerController {

    /**
     * contains the sprite image.
     */
    private static BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
            neutralUp, neutralDown, neutralLeft, neutralRight;
    /**
     * Singleton.
     */
    private static PlayerController instance;
    /**
     * Reference to KeyInput object to get current input from user.
     */
    private final KeyInput myKeys;
    /**
     *
     */
    private int mySpriteNum = 1;
    /**
     * The direction of the player.
     */
    public String myDirection;
    /**
     * The old direction of the player.
     */
    public String myDirectionMemory;

    /**
     *
     */
    private int spriteCounter = 0;


    /**
     * Initializes player object.
     */
    private PlayerController() {
        myKeys = TMPanel.getTriviaMaze().getKeys();
        initPlayer();
        loadSprites();
        updateCurrentRoom();
    }

    /**
     * Global point of access to instance of PlayerController.
     */
    public static PlayerController getInstance() {
        if(instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }

    /**
     * Executes necessary updates for each frame.
     */
    public void frameUpdate() {
        updatePlayerLocation();
        updateCurrentRoom();
    }

    /**
     * Draws player sprite position based on key press or depress.
     * @param graph
     * @param myLocationX
     * @param myLocationY
     */
    public void drawMe(final Graphics2D graph, final int myLocationX, final int myLocationY) {
        BufferedImage image = neutralDown;
        switch (myDirection) {
            case "up" -> {
                if (mySpriteNum == 1) {
                    image = up1;
                }
                if (mySpriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (mySpriteNum == 1) {
                    image = down1;
                }
                if (mySpriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (mySpriteNum == 1) {
                    image = left1;
                }
                if (mySpriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (mySpriteNum == 1) {
                    image = right1;
                }
                if (mySpriteNum == 2) {
                    image = right2;
                }
            }
            default -> {
                image = switch (myDirectionMemory) {
                    case "up" -> neutralUp;
                    case "down" -> neutralDown;
                    case "left" -> neutralLeft;
                    case "right" -> neutralRight;
                    default -> image;
                };
                myDirection = myDirectionMemory;
            }
        }
        graph.drawImage(image, myLocationX, myLocationY, TMPanel.TILE_SIZE,
                TMPanel.TILE_SIZE, null);
    }

    /**
     * Reads key press input and updates player location accordingly.
     */
    private void updatePlayerLocation() {
        if (myKeys.up() || myKeys.down() || myKeys.left() || myKeys.right() || myKeys.neutral()) {
            if (myKeys.up()) {
                myDirection = "up";
                movePlayer();
            } else if (myKeys.down()) {
                myDirection = "down";
                movePlayer();
            } else if (myKeys.left()) {
                myDirection = "left";
                movePlayer();
            } else if (myKeys.right()) {
                myDirection = "right";
                movePlayer();
            }
            //tracks which directional sprite image to use
            spriteCounter++;
            if (spriteCounter > 2) {
                if (mySpriteNum == 1) {
                    mySpriteNum = 2;
                } else if (mySpriteNum == 2) {
                    mySpriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        // remembers player's direction to load correct neutral sprite
        if (!myKeys.up() && !myKeys.down() && !myKeys.right() && !myKeys.left() && myKeys.neutral()) {
            switch (myDirection) {
                case "down" -> myDirectionMemory = "down";
                case "up" -> myDirectionMemory = "up";
                case "left" -> myDirectionMemory = "left";
                case "right" -> myDirectionMemory = "right";
            }
            myDirection = ""; // reset direction
        }
    }

    /**
     * Grabs the current position of the player, checks the position the player is requesting to go,
     * and returns is the tile is collidable or not.
     * @param dir
     * @return
     */
    private boolean isTileCollidable(final String dir) {

        // stores player location x and y
        int xLocation = Player.getInstance().getPlayerLocationX();
        int yLocation = Player.getInstance().getPlayerLocationY();
        // uses player location x,y to find which tile in the maze player is on
        int mapRow = yLocation / 24;
        int mapCol = xLocation / 24;

        // prepares to check tiles to be advanced to
        switch (dir) {
            case "up" -> mapRow -= 1;
            case "down" -> mapRow += 1;
            case "left" -> mapCol -= 1;
            case "right" -> mapCol += 1;
        }

        // stores tile player is wanting to advance to
        int tileNum = TileManager.getInstance().getMapData()[mapRow][mapCol];
        //returns true if tile is collidable and player cannot advance to it
        return TileManager.getInstance().getTile(tileNum).isCollidable();

    }

    /**
     * Updates player location after checking if move is legal (collidable)
     */
    private void movePlayer() {
        if (!isTileCollidable(myDirection)) {
            switch (myDirection) {
                case "up" -> Player.getInstance().setLocationY(-Player.getInstance().getSpeed());
                case "down" -> Player.getInstance().setLocationY(Player.getInstance().getSpeed());
                case "left" -> Player.getInstance().setLocationX(-Player.getInstance().getSpeed());
                case "right" -> Player.getInstance().setLocationX(Player.getInstance().getSpeed());
            }
        }
    }

    /**
     * Does some math to compute which room of the maze the player is in using their location.
     */
    private void updateCurrentRoom() {
        int tileX = Player.getInstance().getPlayerLocationX() / TMPanel.TILE_SIZE;
        int tileY = Player.getInstance().getPlayerLocationY() / TMPanel.TILE_SIZE;

        int roomX = tileX % 6 == 0 ? -1 : (int) Math.ceil(tileX / 6f);
        int roomY = tileY % 6 == 0 ? -1 : (int) Math.ceil(tileY / 6f);

        Room currentRoom = TriviaMaze.getInstance().getRoom(roomX - 1, roomY - 1);
        if(TriviaMazeController.getInstance().playerInNewRoom(currentRoom)) {
            TriviaMazeController.getInstance().enteredNewRoom();
        }
    }

    /**
     * Sets default values for the player.
     */
    private void initPlayer() {
        Player.getInstance().setLocationY(3 * TMPanel.TILE_SIZE);
        Player.getInstance().setLocationX(3 * TMPanel.TILE_SIZE);
        Player.getInstance().setSpeed(TMPanel.TILE_SIZE);
        myDirection = "neutral";
        myDirectionMemory = "neutral";
        myKeys.setNeutral(true);
    }

    /**
     * Stores player sprites in directional variables.
     */
    private void loadSprites() {
        try {
            // up sprites
            up1 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_up1.png"));
            up2 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_up2.png"));
            neutralUp = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/player/player_up_neutral.png"));
            // down sprites
            down1 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_down1.png"));
            down2 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_down2.png"));
            neutralDown = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_down_neutral.png"));
            // right sprites
            right1 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_right1.png"));
            right2 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_right2.png"));
            neutralRight = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_right_neutral.png"));
            // left sprites
            left1 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/player/Player_left1.png"));
            left2 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/player/Player_left2.png"));
            neutralLeft = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_left_neutral.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
