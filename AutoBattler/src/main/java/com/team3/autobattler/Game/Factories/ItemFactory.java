package com.team3.autobattler.Game.Factories;

import java.util.HashMap;
import java.util.Map;

import com.team3.autobattler.Game.Base.Item;
import com.team3.autobattler.Game.Base.ItemType;


public class ItemFactory {
    private static Map<String, Item> items = new HashMap<>();

    public static Item getItem(ItemType itemType) {
        // Generate a unique key for the item based on ItemType
        String key = itemType.getName() + "_" + itemType.getAbility();

        // Check if an item with the same key already exists in the map
        Item item = items.get(key);

        // If not, create a new item and add it to the map
        if (item == null) {
            item = new Item(itemType);
            items.put(key, item);
        }

        return item;
    }
}
