/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.items;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Inventory class that contains the items that player can hold
 *
 * @author Minh Le
 */
public class ItemInventory implements Serializable {

    /**
     * A list of item in the inventory
     */
    private final ArrayList<Item> myInventory;

    /**
     * A list of item in the inventory
     */
    private final int MAX_INVENTORY = 6;

    /**
     * Constructor that create an empty inventory for player
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
        if(myInventory == null || myInventory.size() < MAX_INVENTORY ){
            myInventory.add(theItemObject);
        }
        else {
            System.out.println("Inventory is full. Cannot add item.");
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
        else {
            System.out.println("Inventory is empty. Cannot remove item.");
        }
    }

    /**
     * Clone item and add it to the inventory
     * @param theItemObject to be cloned
     */
    public void cloneItem(final Item theItemObject){
        addItem(theItemObject);
    }

    /**
     * List item in the inventory with name, description and type
     */
    public void listItem(){
        int index = 1;

        if(myInventory.size() > 0) {
            System.out.println("Your inventory has the following items:");
            for (Item item : myInventory) {
                System.out.println(index + " " + item.getItemName() +
                                "Description: " + item.getItemDescription() +
                                " Type: " + item.getItemType());
                index++;
            }
        } else {
            System.out.println("Your inventory is empty.");
        }
    }

    /**
     * When the user uses this item, this class return a string of item's function
     * so the question will be changed based on the function of this item.
     * Item is removed from the inventory
     * @param itemType type of the item in the inventory
     * @return item that is used.
     */
    public Item useItem(final String itemType) {
        Item selectedItem = null;
        if(containItem(itemType)){
            for(int i = 0; i < myInventory.size(); i++){
                if(myInventory.get(i).getItemType().equalsIgnoreCase(itemType)){
                    selectedItem = myInventory.get(i);
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
     * @param itemIndex index of the item
     * @return item at a specific index
     */
    public Item getItem(int itemIndex) {
        return myInventory.get(itemIndex);
    }

    /**
     * Check whether if inventory contains a specific type
     * @param itemType type of the item
     * @return true if item exists in the inventory
     */
    public boolean containItem(String itemType) {
        for(int i = 0; i < myInventory.size(); i++){
            if(myInventory.get(i).getItemType().equalsIgnoreCase(itemType)){
                return true;
            }
        }
        return false;
    }

    /**
     * Maximum number of items in the inventory
     * @return maximum number of items in the inventory
     */
    public int getMaxInventory() {
        return MAX_INVENTORY;
    }
}
