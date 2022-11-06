package model;

/**
 * Item abstract class
 */
public abstract class Item {
    private String myItemName;
    private String myItemFunction;

    public String getItemName(){
        return this.myItemName;
    }

    public String getItemFunction(){
        return this.myItemFunction;
    }
}
