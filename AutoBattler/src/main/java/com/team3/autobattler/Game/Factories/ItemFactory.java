package com.team3.autobattler.Game.Factories;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.team3.autobattler.Game.Base.Item;
import com.team3.autobattler.Game.Base.ItemType;

/**
 * The ItemFactory class is responsible for creating and managing Item objects.
 * It follows the Singleton pattern to ensure a single instance of the factory.
 * 
 * @author [Your Name]
 */
public class ItemFactory {
    private Map<Integer, Item> items = new HashMap<>();
    private static ItemFactory itemfactory = null;
    
    /**
     * Retrieves a randomly selected Item from the factory's collection.
     *
     * @return A randomly selected Item.
     */
    public Item getItem() {
        Random rand = new Random();
        int key = rand.nextInt(items.size());
        return items.get(key);
    }
    
    /**
     * Retrieves the number of items in the factory's collection.
     *
     * @return The number of items in the factory.
     */
    public int getNumItems()
    {
        return items.size();
    }
    
    /**
     * Initializes the factory's collection with predefined Item objects.
     */
    private void initializeItems()
    {
        items.put(items.size(), new Item(new ItemType("testOne","testOne")));
        items.put(items.size(), new Item(new ItemType("testTwo","testTwo")));
        items.put(items.size(), new Item(new ItemType("testThree","testThree")));
        items.put(items.size(), new Item(new ItemType("testFour","testFour")));
    }
    
    /**
     * Private constructor to prevent external instantiation.
     * Initializes the factory with predefined items.
     */
    private ItemFactory() 
    {
    initializeItems();
    } 
    
    /**
     * Retrieves the singleton instance of the ItemFactory.
     *
     * @return The singleton instance of the ItemFactory.
     */
    public static ItemFactory getInstance() 
    { 
        if (itemfactory == null) 
        {
            itemfactory = new ItemFactory(); 
            
        }
  
        return itemfactory; 
    } 
}
