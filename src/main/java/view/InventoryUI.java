package view;

import model.items.Item;
import model.items.ItemInventory;
import model.mazeElements.TriviaMaze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class InventoryUI {

    /**
     * Constant for the width of the inventory window.
     */
    public static final int WIDTH = 150;

    /**
     * Constant for the height of the inventory window.
     */
    public static final int HEIGHT = 400;

    /**
     * Constant for the height of the inventory panel.
     */
    public static final int PANEL_HEIGHT = 150;

    /**
     * Draw an inventory image
     */
    void inventoryImage(JPanel theSidebar){

        ItemInventory myInventory = TriviaMaze.getInstance().inventory();

        // Inventory window inside the Sidebar window
        JPanel inventoryWindow = new JPanel();
        inventoryWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        inventoryWindow.setLayout(new BorderLayout());
        inventoryWindow.setBackground(Color.GRAY);
        theSidebar.add(inventoryWindow);

        // Inventory panel inside Inventory Window and contains label and inventory
        JPanel inventoryPanel = new JPanel(new BorderLayout());
        inventoryPanel.setPreferredSize(new Dimension(WIDTH, PANEL_HEIGHT));
        inventoryPanel.setBackground(Color.GRAY);
        inventoryWindow.add(inventoryPanel, BorderLayout.SOUTH);

        // Inventory label inside Inventory Panel
        JLabel inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inventoryPanel.add(inventoryLabel, BorderLayout.NORTH);

        // Inventory box inside Inventory Panel
        JPanel inventoryBox = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 3);
        gridLayout.setHgap(10);
        inventoryBox.setLayout(gridLayout);
        inventoryBox.setOpaque(false);
        inventoryPanel.add(inventoryBox, BorderLayout.SOUTH);

        Item newItem;
        String itemName;

        try {
            String itemImagePath = "/icons/question-mark.png";
            for(int i = 0; i < myInventory.inventorySize(); i++){
                ImageIcon itemIcon = new ImageIcon();
                newItem = myInventory.getItem(i);
                itemName = newItem.getItemName().toLowerCase();
                itemImagePath = "/tiles/" + itemName + ".png";

                // Read png file, resize the image, and add image to icon.
                BufferedImage iconImage = ImageIO.read
                        (Objects.requireNonNull(getClass().getResourceAsStream(itemImagePath)));
                Image newImage = iconImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                itemIcon.setImage(newImage);

                // Add item icon
                JLabel iconLabel = new JLabel(itemIcon);
                iconLabel.setToolTipText(newItem.getItemDescription());
                inventoryBox.add(iconLabel);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
