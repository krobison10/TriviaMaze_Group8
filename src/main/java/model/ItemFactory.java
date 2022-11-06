package model;

/**
 * Item Factory that create new item
 */
public class ItemFactory {
    public Item createItem(String itemName, String itemFunction)
    {
        if(itemFunction.equalsIgnoreCase("fifty option"))
        {
            return new ItemFiftyFiftyOption(itemName,itemFunction);
        }
        if(itemFunction.equalsIgnoreCase("change question"))
        {
            return new ItemQuestionChange(itemName,itemFunction);
        }
        else{
            throw new IllegalArgumentException("Illegal Argument");
        }
    }
}
