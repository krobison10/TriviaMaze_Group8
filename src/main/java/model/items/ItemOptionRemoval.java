/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.items;

/**
 * Item that eliminate two wrong answer from multiple choice question
 *
 * @author Minh Le
 */
public class ItemOptionRemoval extends Item {

    /**
     * Constructor to create item to remove 2 wrong answers
     * @param theItemName name of the item to be created
     * @param theItemDescription description of the item to be created
     * @param theItemType type of the item to be created
     */
    public ItemOptionRemoval(final String theItemName, final String theItemDescription, final String theItemType) {
        super.setItemName(theItemName);
        super.setItemDescription(theItemDescription);
        super.setItemType(theItemType);
    }
}
