package model;

/**
 * Item that eliminate two wrong answer from multiple choice question
 */
public class ItemOptionRemoval extends Item {

    /**
     * Constructor to create item to remove 2 wrong answers
     * @param theItemName name of the item to be created
     * @param theItemDescription description of the item to be created
     * @param theItemType type of the item to be created
     */
    ItemOptionRemoval(String theItemName, String theItemDescription, String theItemType) {
        super.setItemName(theItemName);
        super.setItemDescription(theItemDescription);
        super.setItemType(theItemType);
    }
}
