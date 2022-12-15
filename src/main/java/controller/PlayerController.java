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
 * Handles game logic concerning the animation, movement, and collision of the player.
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
     * Singleton instance.
     */
    private static PlayerController instance;
    /**
     * Reference to KeyInput object to get current input from user.
     */
    private final KeyInput myKeys;
    /**
     * Number of the sprite for animation
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
     * Counter for keyframe animation
     */
    private int mySpriteCounter = 0;


    /**
     * Initializes player object.
     * Private for singleton
     */
    private PlayerController() {
        myKeys = new KeyInput();
        TMPanel.getInstance().addKeyListener(myKeys);
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
     * Resets the instance by setting the field to null.
     * Next time getInstance() is called, a new instance will be created.
     * Enables singleton to work with serialization and game restarting.
     */
    public static void resetInstance() {
        instance = null;
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
     * @param theGraph Graphics2D object to draw to.
     * @param theX the X location of the player.
     * @param theY the Y location of the player.
     */
    public void drawMe(final Graphics2D theGraph, final int theX, final int theY) {
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
        theGraph.drawImage(image, theX, theY, TMPanel.TILE_SIZE, TMPanel.TILE_SIZE, null);
    }

    /**
     * Reads key press input and updates player location accordingly.
     */
    private void updatePlayerLocation() {
        if (myKeys.getMyUp() || myKeys.getMyDown() || myKeys.getMyLeft()
                || myKeys.getMyRight() || myKeys.getMyNeutral()) {

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
     * Determines if the immediately adjacent tile is collidable or not.
     * @param theDir the direction to check in.
     * @return if there is a collidable tile immediately adjacent in the direction.
     */
    private boolean isTileCollidable(final String theDir) {

        // stores player location x and y
        int xLocation = TriviaMaze.getInstance().player().getLocationX();
        int yLocation = TriviaMaze.getInstance().player().getLocationY();
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
        Player p = TriviaMaze.getInstance().player();
        if (!isTileCollidable(myDirection)) {
            switch (myDirection) {
                case "up" -> p.changeY(-p.getSpeed());
                case "down" -> p.changeY(p.getSpeed());
                case "left" -> p.changeX(-p.getSpeed());
                case "right" -> p.changeX(p.getSpeed());
            }
        // used to go through walls within the maze.
        } else if (myKeys.getMyCheat()) {
            switch (myDirection) {
                case "up" -> p.changeY(-p.getSpeed() * 2);
                case "down" -> p.changeY(p.getSpeed() * 2);
                case "left" -> p.changeX(-p.getSpeed() * 2);
                case "right" -> p.changeX(p.getSpeed() * 2);
            }
        }
    }

    /**
     * Does some math to compute which room of the maze the player is in using their location.
     */
    private void updateCurrentRoom() {
        int tileX = TriviaMaze.getInstance().player().getLocationX() / TMPanel.TILE_SIZE;
        int tileY = TriviaMaze.getInstance().player().getLocationY() / TMPanel.TILE_SIZE;

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
