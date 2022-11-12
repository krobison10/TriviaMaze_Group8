package model;

/**
 * Factory for item objects.
 */
class ItemFactory {
    /**
     * Returns a subclass instance of Item based on request.
     * @param theName the name of the item to be created.
     * @param theDescription the description of the item to be created.
     * @param theType the type of item to be created. Can be either
     *                'fifty option' or 'change question', otherwise
     *                an exception will be thrown.
     * @return an instance of an Item.
     */
    static Item createItem(final String theName, final String theDescription, final String theType) {

        if(theType.equalsIgnoreCase("fifty option")) {
            return new ItemFiftyFiftyOption(theName, theDescription, theType);
        }

        if(theType.equalsIgnoreCase("change question")) {
            return new ItemQuestionChange(theName, theDescription, theType);
        }

        throw new IllegalArgumentException("ItemFactory: invalid Item instance requested");
    }
}