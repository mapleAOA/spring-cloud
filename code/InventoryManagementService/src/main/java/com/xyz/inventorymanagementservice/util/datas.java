package com.xyz.inventorymanagementservice.util;

import com.xyz.inventorymanagementservice.beans.Inventory;
import com.xyz.inventorymanagementservice.beans.Material;

import java.util.HashMap;
import java.util.Map;

public class datas {
    public static Map<String, Material> materialDatabase = new HashMap<>();
    static {
        Material gold=new Material("1","gold");
        Material iron=new Material("2","iron");
        materialDatabase.put(gold.getId(),gold);
        materialDatabase.put(iron.getId(), iron);
    }
    public static Map<String, Inventory> inventoryDatabase = new HashMap<>();
    static {
        Material gold=new Material("1","gold");
        Material iron=new Material("2","iron");
        Map <Material, Integer> i1=new HashMap<>();
        Map <Material, Integer> i2=new HashMap<>();
        i1.put(gold,100);
        i1.put(iron,100);
        i2.put(gold,150);
        i2.put(iron,50);
        Inventory inventory01=new Inventory("1", i1);
        Inventory inventory02=new Inventory("2", i2);
        inventoryDatabase.put(inventory01.getId(),inventory01);
        inventoryDatabase.put(inventory02.getId(),inventory02);
    }

}
