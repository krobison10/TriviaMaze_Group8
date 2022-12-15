/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Inventory class that contains the items that player can hold
 *
 * @author Minh Le
 */
public class ItemInventory implements Serializable {

    /**
     * A list of item in the inventory
     */
    public static final int MAX_INVENTORY = 6;

    /**
     * A list of item in the inventory
     */
    private final List<Item> myInventory;


    /**
     * Constructor that creates an empty inventory for player
     */
    public ItemInventory() {
        myInventory = new ArrayList<>();
    }

    /**
     * Get inventory's size
     * @return number of items in the inventory
     */
    public int inventorySize(){
        return myInventory.size();
    }

    /**
     * Add item to the inventory if player's inventory has less than 3 items
     * @param theItemObject that added to the inventory
     */
    public void addItem(final Item theItemObject){
        if( myInventory.size() < MAX_INVENTORY ){
            myInventory.add(theItemObject);
        }
    }

    /**
     * Remove item from the inventory if inventory is not empty
     * @param theItemObject item object to be removed
     */
    public void removeItem(final Item theItemObject){
        if(myInventory.size() > 0){
            myInventory.remove(theItemObject);
        }
    }

    /**
     * When the user uses this item, this class return a string of item's function
     * so the question will be changed based on the function of this item.
     * Item is removed from the inventory
     * @param theItemType type of the item in the inventory
     * @return item that is used.
     */
    public Item useItem(final String theItemType) {
        Item selectedItem = null;
        if(containsItem(theItemType)){
            for (Item item : myInventory) {
                if (item.getItemType().equalsIgnoreCase(theItemType)) {
                    selectedItem = item;
                    removeItem(selectedItem);
                    break;
                }
            }
            return selectedItem;
        } else {
            return null;
        }
    }

    /**
     * Return an index of an item
     * @param theItemIndex index of the item
     * @return item at a specific index
     */
    public Item getItem(final int theItemIndex) {
        return myInventory.get(theItemIndex);
    }

    /**
     * Check whether if inventory contains a specific type
     * @param theItemType type of the item
     * @return true if item exists in the inventory
     */
    public boolean containsItem(final String theItemType) {
        for (Item item : myInventory) {
            if (item.getItemType().equalsIgnoreCase(theItemType)) {
                return true;
            }
        }
        return false;
    }
}
