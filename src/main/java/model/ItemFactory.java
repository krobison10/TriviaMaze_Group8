package model;

/**
 * Item Factory that create new item
 */
public class ItemFactory {
    public Item createItem(String theItemName, String theItemFunction)
    {
        if(theItemFunction.equalsIgnoreCase("fifty option"))
        {
            return new ItemFiftyFiftyOption(theItemName,theItemFunction);
        }
        if(theItemFunction.equalsIgnoreCase("change question"))
        {
            return new ItemQuestionChange(theItemName,theItemFunction);
        }
        else{
            throw new IllegalArgumentException("Illegal Argument");
        }
    }
}
