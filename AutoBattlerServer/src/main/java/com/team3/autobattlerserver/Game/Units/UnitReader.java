/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game.Units;

import com.team3.autobattlerserver.Game.Unit;
import com.team3.autobattlerserver.Game.UnitFactory;
import com.team3.autobattlerserver.Game.UnitType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class UnitReader {
    
    public static String version = null;
    public static List<Unit> values = new ArrayList<>();
    
    static JSONObject readConfig() {
        String settings = "{\"earth\":{\"id\":0,\"health\":3,\"attack\":3,\"level\":1,\"img\":\"url\"}}";
        JSONObject obj = new JSONObject(settings);
        
        return obj;
    }
    
    static {
        //System.out.println(values.size());
        // Generate default units 
        JSONObject config = UnitReader.readConfig();
       // System.out.println("UnitReader " + config.keySet());
        
//               System.out.println("adada " + config);

        JSONObject structure = (JSONObject) config.get("earth");
        
       // System.out.println(structure);
        
        String name = "earth";
            JSONObject l = (JSONObject) config.get("earth");
            
            try {
                //JSONObject rec = config.getJSONObject(0);
                int health = l.getInt("health");
                int attack = l.getInt("attack");
                Field field = UnitReader.class.getDeclaredField("values");
                UnitType type = UnitFactory.getUnitType(name, name + " Ability", 0);  //messed with this

                Unit newUnit = new Unit(attack, health, type);

                Object list = field.getType();
//System.out.println("field - " + field.toString());
//                System.out.println("list - " + list.toString());
                
                Method add = List.class.getDeclaredMethod("add", Object.class);
//                
//                                System.out.println("4324 - " + list.toString());

                field.getType().getMethod("add", Object.class).invoke(values,
                        new Unit(attack, health, type));

                
            } catch (Exception e) {
                            System.out.println(e);

            }
        
    }
    
    
    public UnitReader() {

    }
    
}
