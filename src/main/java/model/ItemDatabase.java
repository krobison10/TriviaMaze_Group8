package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.sqlite.SQLiteDataSource;
/**
 * Item database
 */
public class ItemDatabase {

    /**
     * The list of Item initialized from the database.
     */
    private List<Item> myItemList;
    private int mySize;

    /**
     * Constructor to create an Array List of item database
     */
    ItemDatabase() {
        this.myItemList = createItemList();
    }

    private List<Item> createItemList(){
        List<Item> itemList = new ArrayList<>();

        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:../TriviaMaze_group8/databases/itemdatabase.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        //now query the database table for all its contents and create new item
        String query = "SELECT * FROM itemdatabase";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            //create item using item factory
            //add new item to the item list
            while ( rs.next() ) {
                String itemName = rs.getString( "Name" );
                String description = rs.getString( "Description" );
                String itemType = rs.getString( "Type" );

                Item newItem = ItemFactory.createItem(itemName, description, itemType);
                itemList.add(newItem);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        this.mySize = itemList.size();
        return itemList;
    }

    public int getMySize() {
        return mySize;
    }

    /**
     * Gets an item at a certain index in the list.
     * @param thePosition the index.
     * @return the Item object
     */
    Item getItem(int thePosition) {
        return myItemList.get(thePosition);
    }

    /**
     * Gets a random item from the list.
     * @return the randomly selected Item.
     */
    Item getRandomItem() {
        Random random = new Random();
        return getItem(random.nextInt(0, myItemList.size()));
    }
}