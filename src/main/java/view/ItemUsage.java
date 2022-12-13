/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.items.Item;
import model.items.ItemInventory;
import model.mazeElements.TriviaMaze;
import model.questions.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * Class that add Get Hint button and show hints for the questions
 *
 * @author Minh Le
 */
public class ItemUsage {

    /**
     * Represents the boolean value whether if the user used a help item for this question.
     * if the player used a help item, getHelped  = true
     * if the player has not used a help item, getHelped  = false
     */
    private boolean getHint;

    /**
     * Contain the item of the room
     */
    private final ItemInventory myInventory;

    /**
     * The Question object from which this popup get its data.
     */
    private Question myQuestion;

    /**
     * Item that is used by the player when they press the button get help
     */
    private static Item usedItem;

    /**
     * Panel of the hint window.
     */
    private JPanel hintWindow;

    /**
     * Constant for the width of the hint window.
     */
    public static final int WIDTH = 260;

    /**
     * Constant for the height of the hint window.
     */
    public static final int HEIGHT = 150;

    public ItemUsage() {
        this.myInventory = TriviaMaze.getInstance().inventory();

        // Hint window inside the Question popup window
        hintWindow = new JPanel();
        hintWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        hintWindow.setLayout(new BorderLayout());
    }

    public void addGetHelpButton(JPanel theWindow, Question theQuestion){
        myQuestion = theQuestion;

        //Create and add button for Item
        JButton itemBtn = new JButton("Get hint");
        itemBtn.addActionListener(e -> {
            if(getHint || myInventory.inventorySize() == 0){
                itemBtn.setEnabled(false);
            } else if (!getHint){
                getHelp();
            }
            SidebarManager.getInstance().updateForCurrentRoom();
            theWindow.revalidate();
            theWindow.repaint();
        });

        // Add button Hint window and add hint window to the popup window question
        hintWindow.add(itemBtn, BorderLayout.NORTH);
        theWindow.add(hintWindow);
    }

    /**
     * Use an item and get hint for the answer
     */
    private void getHelp(){

        String itemType = "";

        // Hint box inside Hint window
        JPanel hintBox = new JPanel();
        hintBox.setLayout(new BoxLayout(hintBox, BoxLayout.Y_AXIS));
        hintWindow.add(hintBox, BorderLayout.SOUTH);

        // Run this code only if the player has not used a help item
        while(!getHint){
            getHint = true;

            // For multiple choice question type with 4 options
            if(myQuestion instanceof QuestionSelection
                    && ((QuestionSelection) myQuestion).getOptions().length == 4){
                    itemType = "option removal";
                }

            // For question type Free answer
            else if(myQuestion instanceof QuestionFA) {
                itemType = "hint display";
            }
            usedItem = myInventory.useItem(itemType);

            if(usedItem != null){

                // Hint label inside Hint window
                String itemUsedText = "You used " + usedItem.getItemName();
                JLabel hintLabel = new JLabel("<html><font size='16' color='orange'><strong>HINT</strong></font><br>"
                        + itemUsedText +"</html>");
                hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
                hintWindow.add(hintLabel, BorderLayout.CENTER);

                // item used icon
                addItemIcon(usedItem);

                // Add hint
                if(myQuestion instanceof QuestionSelection) {
                    multipleChoiceHint(hintBox);
                }
                else {
                    freeAnswerHint(hintBox);
                }

            } else {
                // Add error Message for no item available in the inventory
                String error = "No available item to solve this question.";
                JLabel errorMessage = new JLabel(error);
                hintBox.add(errorMessage);
            }
        }
    }

    /**
     * Update window with hint for multiple choice question
     * @param theWindow updated window with hint
     */
    private void multipleChoiceHint(final JPanel theWindow){
        QuestionSelection q = (QuestionSelection) myQuestion;
        int answerIndex = q.getAnswerPosition();
        String[] optionArray = q.getOptions();
        String correctOption = optionArray[answerIndex];
        String otherOption = "";

        // If question is multiple choice with 4 options
        if(optionArray.length == 4) {
            // Get random index for other option beside the correct option
            int randomIndex = 0;
            Random random = new Random();
            while (randomIndex == answerIndex) {
                randomIndex = random.nextInt(optionArray.length);
            }
            otherOption = optionArray[randomIndex];
        }

        // Create label for options and hide 2 wrong options
        for(String s : optionArray) {
            if(s != null){
                JLabel b = new JLabel(s);

                b.setEnabled(s.equalsIgnoreCase(correctOption) || s.equalsIgnoreCase(otherOption));
                theWindow.add(b);
            }
        }
    }

    /**
     * Update window with hint for free answer question
     * @param theWindow updated window with hint
     */
    private void freeAnswerHint(final JPanel theWindow) {
        QuestionFA q = (QuestionFA) myQuestion;
        JLabel myLabel = new JLabel();
        String hint = q.displayHint();
        myLabel.setText(hint);
        theWindow.add(myLabel);
    }

    /**
     * Add icon of used item to the hint window
     * @param newItem used item for hint
     */
    private void addItemIcon(Item newItem){
        try {
            ImageIcon itemIcon = new ImageIcon();
            String itemName = newItem.getItemName().toLowerCase();
            String itemImagePath = "/tiles/" + itemName + ".png";

            // Read png file, resize the image, and add image to icon.
            BufferedImage iconImage = ImageIO.read
                    (Objects.requireNonNull(getClass().getResourceAsStream(itemImagePath)));
            Image newImage = iconImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            itemIcon.setImage(newImage);

            // Add item icon
            JLabel iconLabel = new JLabel(itemIcon);
            iconLabel.setToolTipText(newItem.getItemDescription());
            hintWindow.add(iconLabel, BorderLayout.EAST);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
