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
import model.tiles.Tiles;
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
    private static BufferedImage myUp1, myUp2, myDown1, myDown2, myLeft1, myLeft2, myRight1, myRight2,
            myNeutralUp, myNeutralDown, myNeutralLeft, myNeutralRight;
    /**
     * Singleton instance.
     */
    private static PlayerController myInstance;
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
    private int mySpriteCounter = 0;


    /**
     * Initializes player object.
     */
    private PlayerController() {
        myKeys = TMPanel.getInstance().getKeys();
        initPlayer();
        loadSprites();
        updateCurrentRoom();
    }

    /**
     * Global point of access to instance of PlayerController.
     */
    public static PlayerController getMyInstance() {
        if(myInstance == null) {
            myInstance = new PlayerController();
        }
        return myInstance;
    }

    /**
     * Resets the instance by setting the field to null.
     * Next time getInstance() is called, a new instance will be created.
     */
    public static void resetInstance() {
        myInstance = null;
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
     * @param theGraph
     * @param theLocationX
     * @param theLocationY
     */
    public void drawMe(final Graphics2D theGraph, final int theLocationX, final int theLocationY) {
        BufferedImage image = myNeutralDown;
        switch (myDirection) {
            case "up" -> {
                if (mySpriteNum == 1) {
                    image = myUp1;
                }
                if (mySpriteNum == 2) {
                    image = myUp2;
                }
            }
            case "down" -> {
                if (mySpriteNum == 1) {
                    image = myDown1;
                }
                if (mySpriteNum == 2) {
                    image = myDown2;
                }
            }
            case "left" -> {
                if (mySpriteNum == 1) {
                    image = myLeft1;
                }
                if (mySpriteNum == 2) {
                    image = myLeft2;
                }
            }
            case "right" -> {
                if (mySpriteNum == 1) {
                    image = myRight1;
                }
                if (mySpriteNum == 2) {
                    image = myRight2;
                }
            }
            default -> {
                image = switch (myDirectionMemory) {
                    case "up" -> myNeutralUp;
                    case "down" -> myNeutralDown;
                    case "left" -> myNeutralLeft;
                    case "right" -> myNeutralRight;
                    default -> image;
                };
                myDirection = myDirectionMemory;
            }
        }
        theGraph.drawImage(image, theLocationX, theLocationY, TMPanel.TILE_SIZE,
                TMPanel.TILE_SIZE, null);
    }

    /**
     * Reads key press input and updates player location accordingly.
     */
    private void updatePlayerLocation() {
        if (myKeys.getMyUp() || myKeys.getMyDown() || myKeys.getMyLeft() || myKeys.getMyRight() || myKeys.getMyNeutral()) {
            if (myKeys.getMyUp()) {
                myDirection = "up";
                movePlayer();
            } else if (myKeys.getMyDown()) {
                myDirection = "down";
                movePlayer();
            } else if (myKeys.getMyLeft()) {
                myDirection = "left";
                movePlayer();
            } else if (myKeys.getMyRight()) {
                myDirection = "right";
                movePlayer();
            }
            //tracks which directional sprite image to use
            mySpriteCounter++;
            if (mySpriteCounter > 2) {
                if (mySpriteNum == 1) {
                    mySpriteNum = 2;
                } else if (mySpriteNum == 2) {
                    mySpriteNum = 1;
                }
                mySpriteCounter = 0;
            }
        }
        // remembers player's direction to load correct neutral sprite
        if (!myKeys.getMyUp() && !myKeys.getMyDown() && !myKeys.getMyRight() && !myKeys.getMyLeft() && myKeys.getMyNeutral()) {
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
     * @param theDir
     * @return
     */
    private boolean isTileCollidable(final String theDir) {

        // stores player location x and y
        int xLocation = TriviaMaze.getInstance().player().getMyLocationX();
        int yLocation = TriviaMaze.getInstance().player().getMyLocationY();
        // uses player location x,y to find which tile in the maze player is on
        int mapRow = yLocation / TMPanel.TILE_SIZE;
        int mapCol = xLocation / TMPanel.TILE_SIZE;

        // prepares to check tiles to be advanced to
        switch (theDir) {
            case "up" -> mapRow -= 1;
            case "down" -> mapRow += 1;
            case "left" -> mapCol -= 1;
            case "right" -> mapCol += 1;
        }

        // stores tile player is wanting to advance to
        int tileNum = TriviaMaze.getInstance().tileManager().getMapData()[mapRow][mapCol];

        // checks to see if player has reached the winning tile
        if(tileNum == Tiles.GOLD.ordinal()) {
            TriviaMazeController.getInstance().gameWon();
        }
        // returns true if tile is collidable so player cannot advance to it
        return TriviaMaze.getInstance().tileManager().getTile(tileNum).isCollidable();
    }

    /**
     * Updates player location after checking if move is legal (collidable)
     */
    private void movePlayer() {
        if (!isTileCollidable(myDirection)) {
                Player p = TriviaMaze.getInstance().player();
            switch (myDirection) {
                case "up" -> p.setMyLocationY(-p.getMySpeed());
                case "down" -> p.setMyLocationY(p.getMySpeed());
                case "left" -> p.setMyLocationX(-p.getMySpeed());
                case "right" -> p.setMyLocationX(p.getMySpeed());
            }
        }
    }

    /**
     * Does some math to compute which room of the maze the player is in using their location.
     */
    private void updateCurrentRoom() {
        int tileX = TriviaMaze.getInstance().player().getMyLocationX() / TMPanel.TILE_SIZE;
        int tileY = TriviaMaze.getInstance().player().getMyLocationY() / TMPanel.TILE_SIZE;

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
        myDirection = "neutral";
        myDirectionMemory = "neutral";
        myKeys.setMyNeutral(true);
    }

    /**
     * Stores player sprites in directional variables.
     */
    private void loadSprites() {
        try {
            // up sprites
            myUp1 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_up1.png"));
            myUp2 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_up2.png"));
            myNeutralUp = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/player/player_up_neutral.png"));
            // down sprites
            myDown1 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_down1.png"));
            myDown2 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_down2.png"));
            myNeutralDown = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_down_neutral.png"));
            // right sprites
            myRight1 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_right1.png"));
            myRight2 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_right2.png"));
            myNeutralRight = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_right_neutral.png"));
            // left sprites
            myLeft1 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/player/Player_left1.png"));
            myLeft2 = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/player/Player_left2.png"));
            myNeutralLeft = ImageIO.read(new FileInputStream
                    ("../TriviaMaze_Group8/src/main/resources/Player/player_left_neutral.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
