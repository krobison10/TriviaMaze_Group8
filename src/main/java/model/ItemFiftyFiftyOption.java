package model;

/**
 * Item that eliminate two wrong answer from multiple choice question
 */
public class ItemFiftyFiftyOption extends Item {
    private String myItemName;
    private String myItemFunction;

    // Constructor class for default package
    ItemFiftyFiftyOption(String myItemName, String myItemFunction) {
        this.myItemName = myItemName;
        this.myItemFunction = myItemFunction;
    }

    public String getItemName(){
        return this.myItemName;
    }

    public String getItemFunction(){
        return this.myItemFunction;
    }
}
