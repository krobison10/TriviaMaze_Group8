package model.factories;

import model.items.Item;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemFactoryTest {

    private static Item ruler;
    private static Item eraser;
    private static Item pencil;

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test()
    @DisplayName("Create item to change question")
    void testCreateQuestionChangeItem() {
        ruler = ItemFactory.createItem("Ruler",
                "A ruler that can change the question.",
                "change question");
        assertEquals("change question", ruler.getItemType(),
                "Item ruler should have type change question.");
    }

    @Test()
    @DisplayName("Create item to remove 2 wrong options")
    void testCreateItemToRemoveWrongOptions() {
        eraser = ItemFactory.createItem("Eraser",
                "An eraser that can remove 2 wrong answers.",
                "option removal");
        assertEquals("option removal", eraser.getItemType(),
                "Item eraser should have type option removal.");
    }

    @Test()
    @DisplayName("Create item to display hints for free answer question type")
    void testCreateItemToDisplayHints() {
        pencil = ItemFactory.createItem("Pencil",
                "A pencil that can show some letters in the free answer question type.",
                "hint display");
        assertEquals("hint display", pencil.getItemType(),
                "Item pencil should have type hint display.");
    }

    @Test()
    @DisplayName("Create item with wrong type throw exception")
    void testCreateItemWithWrongType() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ItemFactory.createItem("New Item",
                        "An item that can hide the option.",
                        "hide the options"));
        System.out.println(exception);
    }
}