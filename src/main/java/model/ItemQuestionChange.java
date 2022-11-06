package model;

/**
 * Item that change question
 */
public class ItemQuestionChange extends Item{
    private String myItemName;
    private String myItemFunction;

    // Constructor class for default package
    ItemQuestionChange(String myItemName, String myItemFunction) {
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
