package com.xyz.inventorymanagementservice.service;

import com.xyz.inventorymanagementservice.beans.Inventory;

import java.util.List;

public interface InventoryService {
    public Inventory getInventoryById(String id);
    public List<Inventory> getInventoryList();
    public Inventory InventoryAddProduct(String inventoryId,String materialId, int num);
    public Inventory InventoryRemoveProduct(String inventoryId,String materialId, int num);
    public void Move(String fromId, String toId,String materialId,int num);
}
