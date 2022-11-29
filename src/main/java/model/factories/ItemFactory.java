/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.factories;

import model.items.Item;
import model.items.ItemHintDisplay;
import model.items.ItemOptionRemoval;
import model.items.ItemQuestionChange;

/**
 * Factory for item objects.
 *
 * @author Minh Le
 */
public class ItemFactory {
    /**
     * Returns a subclass instance of Item based on request.
     * @param theName the name of the item to be created.
     * @param theDescription the description of the item to be created.
     * @param theType the type of item to be created. Can be either
     *                'option removal' or 'change question' or 'hint display',
     *                otherwise an exception will be thrown.
     * @return an instance of an Item.
     */
    public static Item createItem(final String theName, final String theDescription, final String theType) {

        if(theType.equalsIgnoreCase("option removal")) {
            return new ItemOptionRemoval(theName, theDescription, theType);
        }

        if(theType.equalsIgnoreCase("change question")) {
            return new ItemQuestionChange(theName, theDescription, theType);
        }

        if(theType.equalsIgnoreCase("hint display")) {
            return new ItemHintDisplay(theName, theDescription, theType);
        }

        throw new IllegalArgumentException("ItemFactory: invalid Item instance requested");
    }
}