package model.items;

/**
 * Item that change question
 */
public class ItemHintDisplay extends Item{
    /**
     * Constructor to create item that display hints for free answer question type
     * @param theItemName name of the item to be created
     * @param theItemDescription description of the item to be created
     * @param theItemType type of the item to be created
     */
    public ItemHintDisplay(String theItemName, String theItemDescription, String theItemType) {
        super.setItemName(theItemName);
        super.setItemDescription(theItemDescription);
        super.setItemType(theItemType);
    }
}
