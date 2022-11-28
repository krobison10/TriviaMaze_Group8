package controller;

import model.mazeElements.Player;
import model.mazeElements.Room;
import model.mazeElements.TriviaMaze;
import model.tiles.TileManager;
import view.TMPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class PlayerController {

    private final KeyInput keys;
    // Sprite counters to numbered image
    private int spriteCounter = 0;

    private static int spriteNum = 1;
    // Player sprite image variables
    private static BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
            neutralUp, neutralDown, neutralLeft, neutralRight;
    // Player facing direction
    public static String direction;
    // Player's old facing direction
    public static String directionMemory;
    /**
     * Global point of access to instance of PlayerController.
     */
    public static PlayerController instance;

    public static boolean playerCollision = false;


    /**
     * Initializes player object.
     */
    public PlayerController() {
        instance = this;
        keys = TMPanel.getTriviaMaze().getKeys();
        initPlayer();
        getPlayerImage();
        updateCurrentRoom();
    }

    /**
     * Reads key press input and updates player location accordingly.
     */
    public void updatePlayer() {
        if (keys.up || keys.down || keys.left || keys.right || keys.neutral) {
            if (keys.up) {
                direction = "up";
                updatePlayerLocation();
            } else if (keys.down) {
                direction = "down";
                updatePlayerLocation();
            } else if (keys.left) {
                direction = "left";
                updatePlayerLocation();
            } else if (keys.right) {
                direction = "right";
                updatePlayerLocation();
            }
            //tracks which directional sprite image to use
            spriteCounter++;
            if (spriteCounter > 2) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        // remembers player's direction to load correct neutral sprite
        if (!keys.up && !keys.down && !keys.right && !keys.left && keys.neutral) {
            switch (direction) {
                case "down" -> directionMemory = "down";
                case "up" -> directionMemory = "up";
                case "left" -> directionMemory = "left";
                case "right" -> directionMemory = "right";
            }
            direction = ""; // reset direction
        }
    }

    /**
     * Stores player sprites in directional variables.
     */
    public void getPlayerImage() {
        try {
            // up sprites
            up1 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_up1.png"));
            up2 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_up2.png"));
            neutralUp = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/player/player_up_neutral.png"));
            // down sprites
            down1 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_down1.png"));
            down2 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_down2.png"));
            neutralDown = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_down_neutral.png"));
            // right sprites
            right1 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_right1.png"));
            right2 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_right2.png"));
            neutralRight = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_right_neutral.png"));
            // left sprites
            left1 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/player/Player_left1.png"));
            left2 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/player/Player_left2.png"));
            neutralLeft = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_left_neutral.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws player sprite position based on key press or depress
     * @param graph
     * @param myLocationX
     * @param myLocationY
     */
    public static void drawMe(Graphics2D graph, int myLocationX, int myLocationY) {
        BufferedImage image = neutralDown;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            default -> {
                image = switch (directionMemory) {
                    case "up" -> neutralUp;
                    case "down" -> neutralDown;
                    case "left" -> neutralLeft;
                    case "right" -> neutralRight;
                    default -> image;
                };
                direction = directionMemory;
            }
        }
        graph.drawImage(image, myLocationX, myLocationY, TMPanel.TILE_SIZE,
                TMPanel.TILE_SIZE, null);
    }

    /**
     * Player default values.
     */
    public void initPlayer() {
        Player.instance.setLocationY(3 * TMPanel.TILE_SIZE);
        Player.instance.setLocationX(3 * TMPanel.TILE_SIZE);
        Player.instance.setSpeed(TMPanel.TILE_SIZE);
        direction = "neutral";
        directionMemory = "neutral";
        keys.neutral = true;
    }

    public void updateCurrentRoom() {
        int tileX = Player.instance.getPlayerLocationX() / TMPanel.TILE_SIZE;
        int tileY = Player.instance.getPlayerLocationY() / TMPanel.TILE_SIZE;

        int roomX = tileX % 6 == 0 ? -1 : (int) Math.ceil(tileX / 6f);
        int roomY = tileY % 6 == 0 ? -1 : (int) Math.ceil(tileY / 6f);

        Room currentRoom = TriviaMaze.instance.getRoom(roomX - 1, roomY - 1);
    }
    /**
     * Grabs the current position of the player, checks the position the player is requesting to go,
     * and returns is the tile is collidable or not.
     * @param dir
     * @return
     */
    public boolean isTileCollidable(String dir) {

        // stores player location x and y
        int xLocation = Player.instance.getPlayerLocationX();
        int yLocation = Player.instance.getPlayerLocationY();
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
        int tileNum = TileManager.instance.getMapData()[mapRow][mapCol];
        //returns true is tile is collidable and player cannot advance to it
        return TileManager.instance.getTile(tileNum).isCollidable();

    }

    /**
     * Updates player location after checking if move is legal (collidable)
     */
    public void updatePlayerLocation() {

        playerCollision = isTileCollidable(direction);

        if (!playerCollision) {
            switch (direction) {
                case "up" -> Player.instance.setLocationY(-Player.instance.getSpeed());
                case "down" -> Player.instance.setLocationY(Player.instance.getSpeed());
                case "left" -> Player.instance.setLocationX(-Player.instance.getSpeed());
                case "right" -> Player.instance.setLocationX(Player.instance.getSpeed());
            }
        }
    }
}
