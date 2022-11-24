package controller;

import model.mazeElements.Player;
import model.tiles.TileManager;
import view.TMPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class PlayerController {

    private final Player myPlayer;

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
    public static boolean playerCollision = false;



    /**
     * Initializes player object.
     * @param thePlayer
     * @param theKeys
     */
    public PlayerController(final Player thePlayer, final KeyInput theKeys) {
        myPlayer = thePlayer;
        keys = theKeys;

        initPlayer();
        getPlayerImage();

    }

    /**
     * Reads key press input and updates player location accordingly.
     */
    public void updatePlayer() {
        if (keys.up || keys.down || keys.left || keys.right || keys.neutral) {
            if (keys.up) {
                direction = "up";
                setNewLocationAndCheckCollision();
//                myPlayer.setPlayerLocationY(-myPlayer.getSpeed());
            } else if (keys.down) {
                direction = "down";
                setNewLocationAndCheckCollision();

//                myPlayer.setPlayerLocationY(myPlayer.getSpeed());
            } else if (keys.left) {
                direction = "left";
                setNewLocationAndCheckCollision();

//                myPlayer.setPlayerLocationX(-myPlayer.getSpeed());
            } else if (keys.right) {
                direction = "right";
                setNewLocationAndCheckCollision();

//                myPlayer.setPlayerLocationX(myPlayer.getSpeed());
            }

//            if (!keys.neutral) {
//                playerCollision = testCollision(myPlayer, direction);
//            }
//
//            if (!playerCollision) {
//                switch (direction) {
//                    case "up" -> myPlayer.setPlayerLocationY(-myPlayer.getSpeed());
//                    case "down" -> myPlayer.setPlayerLocationY(myPlayer.getSpeed());
//                    case "left" -> myPlayer.setPlayerLocationX(-myPlayer.getSpeed());
//                    case "right" -> myPlayer.setPlayerLocationX(myPlayer.getSpeed());
//                }
//            }

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
        if (!keys.up && !keys.down && !keys.right && !keys.left && keys.neutral) {
            switch (direction) {
                case "down" -> directionMemory = "down";
                case "up" -> directionMemory = "up";
                case "left" -> directionMemory = "left";
                case "right" -> directionMemory = "right";
            }
            direction = "";
        }
//        direction = "";
    }

    /**
     * Stores player sprites in directional variables.
     */
    public void getPlayerImage() {
        try {
            // up sprites
            up1 = ImageIO.read(new FileInputStream("../TriviaMaze_Group8/src/main/resources/Player/player_down1.png"));
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
        myPlayer.setPlayerLocationY(3 * TMPanel.TILE_SIZE);
        myPlayer.setPlayerLocationX(3 * TMPanel.TILE_SIZE);
        myPlayer.setSpeed(TMPanel.TILE_SIZE);
        direction = "neutral";
        directionMemory = "neutral";
        keys.neutral = true;
    }

    public boolean testCollision(Player mp, String dir) {

        int xLoc = mp.getPlayerLocationX();
        int yLoc = mp.getPlayerLocationY();
        int row = yLoc / 24;
        int col = xLoc / 24;

        switch (dir) {
            case "up" -> row -= 1;
            case "down" -> row += 1;
            case "left" -> col -= 1;
            case "right" -> col += 1;
        }

        int tileNum = TileManager.myMapData[row][col];
        return TileManager.myTiles[tileNum].isCollidable();

    }

    public void setNewLocationAndCheckCollision() {
        if (!keys.neutral) {
            playerCollision = testCollision(myPlayer, direction);
        }

        if (!playerCollision) {
            switch (direction) {
                case "up" -> myPlayer.setPlayerLocationY(-myPlayer.getSpeed());
                case "down" -> myPlayer.setPlayerLocationY(myPlayer.getSpeed());
                case "left" -> myPlayer.setPlayerLocationX(-myPlayer.getSpeed());
                case "right" -> myPlayer.setPlayerLocationX(myPlayer.getSpeed());
            }
        }
    }
}
