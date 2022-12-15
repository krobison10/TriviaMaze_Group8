/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.items;

/**
 * Item that changes the question. Not currently in use in the game.
 *
 * @author Minh Le
 */
public class ItemQuestionChange extends Item {
    /**
     * Constructor to create item that change question
     * @param theItemName name of the item to be created
     * @param theItemDescription description of the item to be created
     * @param theItemType type of the item to be created
     */
    public ItemQuestionChange(final String theItemName, final String theItemDescription, final String theItemType) {
        super.setItemName(theItemName);
        super.setItemDescription(theItemDescription);
        super.setItemType(theItemType);
    }
}
