package model.items;

/**
 * Item abstract class
 */
public abstract class Item {
    private String myItemName;
    private String myItemDescription;
    private String myItemType;

    /**
     * Get the item's name of the item
     * @return name of the item
     */
    public String getItemName(){
        return this.myItemName;
    }

    /**
     * Add the item's name to the item
     * @param theItemName the item's name to be added to the item
     */
    protected void setItemName(String theItemName){
        this.myItemName = theItemName;
    }

    /**
     * Get the item's description of the item
     * @return description of the item
     */
    public String getItemDescription(){
        return this.myItemDescription;
    }

    /**
     * Add the item's description to the item
     * @param theItemDescription description of the item to be added to the item
     */
    protected void setItemDescription(String theItemDescription){
        this.myItemDescription = theItemDescription;
    }

    /**
     * Get the item's type of the item
     * @return type of the item
     */
    public String getItemType(){
        return this.myItemType;
    }
    /**
     * Add the item's type to the item
     * @param theItemType the item's type to be added to the item
     */
    protected void setItemType(String theItemType){
        this.myItemType = theItemType;
    }
}