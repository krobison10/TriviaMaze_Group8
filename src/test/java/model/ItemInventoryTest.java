package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemInventoryTest {

    private static ItemInventory myInventory;
    private static ItemFactory myItemFactory;
    private static Item ruler;
    private static Item eraser;
    private static Item pencil;

    @BeforeEach
    void setUpBeforeClass() throws Exception {
        myInventory = new ItemInventory();
        myItemFactory = new ItemFactory();
        ruler = myItemFactory.createItem("Ruler",
                "A ruler that can change the question.",
                "change question");
        eraser = myItemFactory.createItem("Eraser",
                "An eraser that can remove 2 wrong answers.",
                "option removal");
        pencil = myItemFactory.createItem("Pencil",
                "A pencil that can show some letters in the free answer question type.",
                "hint display");
    }

    @AfterEach
    void tearDownAfterClass() throws Exception {
        myInventory = new ItemInventory();
    }

    @Test()
    @DisplayName("Add 1 item when inventory is empty")
    void testAddItemToEmptyInventory() {
        myInventory.addItem(ruler);
        assertEquals(1, myInventory.inventorySize(),
                "Inventory should has 1 item after adding 1 item.");
    }

    @Test()
    @DisplayName("Add Item when inventory is full")
    void testAddItemToFullInventory() {
        myInventory.addItem(ruler);
        myInventory.addItem(eraser);
        myInventory.addItem(pencil);
        myInventory.addItem(ruler);
        assertEquals(3, myInventory.inventorySize(),
                "Inventory should has 3 items because it is impossible to add more item to a full inventory.");
    }

    @Test()
    @DisplayName("Remove 1 item from the full inventory")
    void testRemoveItemFromFullInventory() {
        myInventory.addItem(ruler);
        myInventory.addItem(eraser);
        myInventory.addItem(pencil);
        myInventory.removeItem(ruler);
        assertEquals(2, myInventory.inventorySize(),
                "Inventory should has 2 items after removing 1 item.");
    }

    @Test()
    @DisplayName("Remove 1 item from the empty inventory")
    void testRemoveItemFromEmptyInventory() {
        myInventory.removeItem(ruler);
        assertEquals(0, myInventory.inventorySize(),
                "Inventory is empty, cannot remove any item.");
    }

    @Test()
    @DisplayName("Remove 1 item from the inventory with 1 item")
    void testRemoveItemFromInventoryWith1Item() {
        myInventory.addItem(ruler);
        myInventory.removeItem(ruler);
        assertEquals(0, myInventory.inventorySize(),
                "Inventory should has zero item.");
    }

    @Test()
    @DisplayName("Remove item that is not avaiblable in the inventory.")
    void testRemoveItemNotAvailableInInventory() {
        myInventory.addItem(ruler);
        myInventory.removeItem(eraser);
        assertEquals(1, myInventory.inventorySize(),
                "The item that you want to remove is not available in the inventory.");
    }

    @org.junit.jupiter.api.Test
    void cloneItem() {
    }

    @org.junit.jupiter.api.Test
    void listItem() {
    }

    @Test()
    @DisplayName("Use Item Question Change number 1.")
    void useItemQuestionChange() {
        myInventory.addItem(ruler);
        myInventory.addItem(eraser);
        myInventory.addItem(pencil);
        assertEquals("change question", myInventory.useItem(1),
                "Ruler should be used and the type of item is question change.");
    }

    @Test()
    @DisplayName("Use Item when Inventory is empty.")
    void useItemInEmptyInventory() {
        assertEquals("", myInventory.useItem(1),
                "No item is used because Inventory is empty.");
    }
}