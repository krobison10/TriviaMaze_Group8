/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package model.items;

import java.util.ArrayList;

/**
 * Inventory class that contains the items that player can hold
 *
 * @author Minh Le
 */
public class ItemInventory {

    /**
     *
     */
    private final ArrayList<Item> myInventory;

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
        if(myInventory == null || myInventory.size() < 3 ){
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
     * @param theItemIndex index of the item in the inventory
     * @return type of the item that is used.
     */
    public String useItem(final int theItemIndex) {
        if(theItemIndex > 0 && theItemIndex <= myInventory.size()){
            Item selectedItem = myInventory.get(theItemIndex - 1);
            System.out.println("You use item " + selectedItem.getItemName() +
                    "Description: " + selectedItem.getItemDescription() +
                    " Type: " + selectedItem.getItemType());
            String typeOfUsedItem = selectedItem.getItemType();
            removeItem(selectedItem);
            return typeOfUsedItem;
        } else {
            System.out.println("Cannot find item. Please type a correct position number.");
            return "";
        }
    }
}
