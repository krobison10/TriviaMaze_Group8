/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.items;

/**
 * Item that change question
 *
 * @author Minh Le
 */
public class ItemHintDisplay extends Item {
    /**
     * Constructor to create item that display hints for free answer question type
     * @param theItemName name of the item to be created
     * @param theItemDescription description of the item to be created
     * @param theItemType type of the item to be created
     */
    public ItemHintDisplay(final String theItemName, final String theItemDescription, final String theItemType) {
        super.setItemName(theItemName);
        super.setItemDescription(theItemDescription);
        super.setItemType(theItemType);
    }
}
