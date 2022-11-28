package view;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

/**
 * Create a new map with random positions for items in the maze
 */
public class MapGenerator {
    private int numberOfEraser = 3;
    private int numberOfRuler = 3;
    private int numberOfPencil = 3;
    /**
     * Constructor class to create a randommap.txt file
     * using PrintStream to print all System.out.println to text file
     */
    public MapGenerator(){
        PrintStream fileStream = null;
        try {
            fileStream = new PrintStream("../TriviaMaze_Group8/src/main/resources/maps/randommap.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.setOut(fileStream);
        drawRoom();
    }

    /**
     * Create a new maze with walls, doors and items
     */
    private void drawRoom(){
        String fullWall = "1111111111111111111111111111111";
        String middleWall = "1     1     1     1     1     1";
        String bottomWallAndDoor = "1112111112111112111112111112111";
        String finalLineWithPortal = "1     2     2     2     2  6  1";

        for(int i = 0; i < 31; i++){
            // Full wall for the top and bottom
            if(i == 0 || i == 30){
                System.out.println(fullWall);
            }
            // final line with portal icon
            else if (i == 27){
                System.out.println(finalLineWithPortal);
            }
            // Middle line with doors and walls
            else if (i == 6 || i == 12 || i == 18 || i == 24){
                System.out.println(bottomWallAndDoor);
            }
            // Middle line with random items
            else if (i == 3 || i == 9 || i == 15 || i == 21){
                System.out.println(randomLineWithItems());
            }
            // Middle wall with no items and walls
            else {
                System.out.println(middleWall);
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
        if(codeOfItem == 3 && numberOfRuler > 0){
            item = "3";
            numberOfRuler--;
        } else if(codeOfItem == 4 && numberOfEraser > 0){
            item = "4";
            numberOfEraser--;
        } else if(codeOfItem == 5 && numberOfPencil > 0){
            item = "5";
            numberOfPencil--;
        } else {
            item = " ";
        }
        return item;
    }
}
