package model.items;

import model.items.ItemDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for ItemDatabase class
 * @author Minh Le
 */
class ItemDatabaseTest {

    @Test
    @DisplayName("Create item list by reading item objects from database")
    void testCreateItemList() throws Exception {
        ItemDatabase itemList = new ItemDatabase();
        assertEquals(3, itemList.getMySize(),"Item list should have 3 items.");
    }

    @Test
    @DisplayName("Get Item from the position 1")
    void testGetItemPosition1() {
        ItemDatabase itemList = new ItemDatabase();
        Item itemGet = itemList.getItem(1);
        String itemName = itemGet.getItemName();
        assertEquals("Eraser", itemName, "Item 1 should be Eraser.");
    }
}
