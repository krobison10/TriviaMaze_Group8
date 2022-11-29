/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.tiles;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Create a new map with random positions for items in the maze
 *
 * @author Minh Le
 */
public class MapGenerator {
    /**
     *
     */
    private int myNumOfErasers = 3;
    /**
     *
     */
    private int myNumOfRulers = 3;
    /**
     *
     */
    private int myNumOfPencils = 3;


    /**
     * Constructor class to create a randommap.txt file
     * using FileWriter to print text file
     */
    public MapGenerator(){
        FileWriter filename = null;
        try {
            filename = new FileWriter("../TriviaMaze_Group8/src/main/resources/maps/randommap.txt");
            drawRoom(filename);
            filename.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a new maze with walls, doors and items
     */
    private void drawRoom(FileWriter filename) throws IOException {
        String fullWall = "1111111111111111111111111111111";
        String middleWall = "1     1     1     1     1     1";
        String bottomWallAndDoor = "1112111112111112111112111112111";
        String finalLineWithPortal = "1     2     2     2     2  6  1";

        for(int i = 0; i < 31; i++){
            // Full wall for the top and bottom
            if(i == 0 || i == 30){
                filename.write(fullWall + "\n");
            }
            // final line with portal icon
            else if (i == 27){
                filename.write(finalLineWithPortal + "\n");
            }
            // Middle line with doors and walls
            else if (i == 6 || i == 12 || i == 18 || i == 24){
                filename.write(bottomWallAndDoor + "\n");
            }
            // Middle line with random items
            else if (i == 3 || i == 9 || i == 15 || i == 21){
                filename.write(randomLineWithItems() + "\n");
            }
            // Middle wall with no items and walls
            else {
                filename.write(middleWall + "\n");
            }
        }
    }

    /**
     * Create a string with code for doors, walls and items
     * @return a new string with code for doors, walls and items
     */
    private String randomLineWithItems(){
        String text = "";
        for(int i = 0; i < 31; i++){
            if(i == 0 || i == 30){
                text+= "1";
            } else if (i == 6 || i == 12 || i == 18 || i == 24){
                text+= "2";
            } else if (i == 9 || i == 15 || i == 21 || i == 27){
                text+= randomItem();
            } else {
                text+= " ";
            }
        }
        return text;
    }

    /**
     * Return random item
     * "3" for ruler
     * "4" for eraser
     * "5" for pencil
     * " " for no item
     * @return String value of item code
     */
    private String randomItem(){
        Random random = new Random();
        int codeOfItem = random.nextInt(1,6);
        String item;
        if(codeOfItem == 3 && myNumOfRulers > 0){
            item = "3";
            myNumOfRulers--;
        } else if(codeOfItem == 4 && myNumOfErasers > 0){
            item = "4";
            myNumOfErasers--;
        } else if(codeOfItem == 5 && myNumOfPencils > 0){
            item = "5";
            myNumOfPencils--;
        } else {
            item = " ";
        }
        return item;
    }
}
