/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.items;

import java.io.Serializable;

/**
 * Item abstract class
 *
 * @author Minh Le
 */
public abstract class Item implements Serializable {
    /**
     * Name of the item
     */
    private String myItemName;
    /**
     * Description of the item
     */
    private String myItemDescription;
    /**
     * Type of the item
     */
    private String myItemType;

    /**
     * Get the item's name of the item
     * @return name of the item
     */
    public String getItemName(){
        return myItemName;
    }

    /**
     * Get the item's description of the item
     * @return description of the item
     */
    public String getItemDescription(){
        return myItemDescription;
    }

    /**
     * Get the item's type of the item
     * @return type of the item
     */
    public String getItemType(){
        return myItemType;
    }

    /**
     * Add the item's name to the item
     * @param theItemName the item's name to be added to the item
     */
    protected void setItemName(final String theItemName){
        myItemName = theItemName;
    }

    /**
     * Add the item's description to the item
     * @param theItemDescription description of the item to be added to the item
     */
    protected void setItemDescription(final String theItemDescription){
        myItemDescription = theItemDescription;
    }
    /**
     * Add the item's type to the item
     * @param theItemType the item's type to be added to the item
     */
    protected void setItemType(final String theItemType){
        myItemType = theItemType;
    }
}