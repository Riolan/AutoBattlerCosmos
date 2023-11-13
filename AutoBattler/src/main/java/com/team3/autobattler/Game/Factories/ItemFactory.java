package com.team3.autobattler.Game.Factories;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.team3.autobattler.Game.Base.Item;
import com.team3.autobattler.Game.Base.ItemType;


public class ItemFactory {
    private Map<Integer, Item> items = new HashMap<>();
    private static ItemFactory itemfactory = null;

    public Item getItem() {
        Random rand = new Random();
        int key = rand.nextInt(items.size());
        return items.get(key);
    }
    
    public int getNumItems()
    {
        return items.size();
    }
    
    private void initializeItems()
    {
        items.put(items.size(), new Item(new ItemType("testOne","testOne")));
        items.put(items.size(), new Item(new ItemType("testTwo","testTwo")));
        items.put(items.size(), new Item(new ItemType("testThree","testThree")));
        items.put(items.size(), new Item(new ItemType("testFour","testFour")));
    }
    
    private ItemFactory() 
    {
    initializeItems();
    } 
    
    public static ItemFactory getInstance() 
    { 
        if (itemfactory == null) 
        {
            itemfactory = new ItemFactory(); 
            
        }
  
        return itemfactory; 
    } 
}
