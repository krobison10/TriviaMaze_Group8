package model;

import java.util.ArrayList;

/**
 * Inventory class that contains the items that player can hold
 */
public class ItemInventory {

    private ArrayList<Item> myInventory;

    // Constructor that create an inventory for player
    public ItemInventory() {
        ArrayList<Item> myInventory= new ArrayList<Item>();
    }

    // Add item to the inventory
    public void addItem(Item theItemObject){
        if(myInventory.size() < 3){
            myInventory.add(theItemObject);
        }
        else {
            System.out.println("Inventory is full. Cannot add item.");
        }
    }

    // Remove item from the inventory
    public void removeItem(Item theItemObject){
        if(myInventory.size() > 0){
            myInventory.remove(theItemObject);
        }
        else {
            System.out.println("Inventory is empty. Cannot remove item.");
        }
    }

    // Clone item and add it to the inventory
    public void cloneItem(Item theItemObject){
        addItem(theItemObject);
    }

    // List item in the inventory
    public void listItem(){
        int index = 1;

        if(myInventory.size() > 0) {
            System.out.println("Your inventory has the following items:");
            for (Item item : myInventory) {
                System.out.println(index + " " + item.getItemName() +
                                " Function: " + item.getItemFunction());
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
     */
    public String useItem(int theItemIndex) {
        if(theItemIndex > 0 && theItemIndex <= myInventory.size()){
            Item selectedItem = myInventory.get(theItemIndex - 1);
            System.out.println("You use item " + selectedItem.getItemName() +
                    " Function: " + selectedItem.getItemFunction());
            String functionUsedItem = selectedItem.getItemFunction();
            removeItem(selectedItem);
            return functionUsedItem;
        } else {
            System.out.println("Cannot find item. Please type a correct name.");
            return "";
        }
    }
}
