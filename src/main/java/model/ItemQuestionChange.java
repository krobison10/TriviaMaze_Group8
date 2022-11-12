package model;

/**
 * Item that change question
 */
public class ItemQuestionChange extends Item{
    /**
     * Constructor to create item that change question
     * @param theItemName name of the item to be created
     * @param theItemDescription description of the item to be created
     * @param theItemType type of the item to be created
     */
    ItemQuestionChange(String theItemName, String theItemDescription, String theItemType) {
        super.setItemName(theItemName);
        super.setItemDescription(theItemDescription);
        super.setItemType(theItemType);
    }
}
