package com.xyz.inventorymanagementservice.service.impl;

import com.xyz.inventorymanagementservice.beans.Inventory;
import com.xyz.inventorymanagementservice.beans.Material;
import com.xyz.inventorymanagementservice.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.xyz.inventorymanagementservice.util.datas.inventoryDatabase;
import static com.xyz.inventorymanagementservice.util.datas.materialDatabase;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Override
    public Inventory getInventoryById(String id) {
        return inventoryDatabase.get(id);
    }

    @Override
    public List<Inventory> getInventoryList() {
        return new ArrayList<>(inventoryDatabase.values());
    }

    @Override
    public Inventory InventoryAddProduct(String inventoryId,String materialId, int num) {
        Inventory inventory = inventoryDatabase.get(inventoryId);
        Material material=materialDatabase.get(materialId);
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory with ID " + inventoryId + " not found.");
        }
        // 获取当前 Inventory 中的 materials Map
        Map<Material, Integer> materials = inventory.getMaterials();
        // 检查 materials 中是否已有该 Material
        if (materials.containsKey(material)) {
            // 如果已存在，增加对应 Material 的数量
            materials.put(material, materials.get(material) + num);
        } else {
            // 如果不存在，添加新的 Material 记录
            materials.put(material, num);
        }

        // 更新 Inventory 的 materials 属性（可选，视实现而定）
        inventory.setMaterials(materials);

        // 返回更新后的 Inventory
        return inventory;
    }

    @Override
    public Inventory InventoryRemoveProduct(String inventoryId,String materialId, int num) {
        // 从数据库中获取对应的 Inventory 对象
        Inventory inventory = inventoryDatabase.get(inventoryId);
        Material material=materialDatabase.get(materialId);
        // 如果未找到对应的 Inventory，则抛出异常
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory with ID " + inventoryId + " not found.");
        }

        // 获取当前 Inventory 中的 materials Map
        Map<Material, Integer> materials = inventory.getMaterials();

        // 检查 materials 中是否包含该 Material
        if (!materials.containsKey(material)) {
            throw new IllegalArgumentException("Material with ID " + material.getId() + " not found in inventory.");
        }

        // 获取当前 Material 的数量
        int currentCount = materials.get(material);

        // 检查是否有足够的库存数量
        if (currentCount < num) {
            throw new IllegalArgumentException("Not enough inventory for Material with ID " + material.getId() + ". Current count: " + currentCount);
        }

        // 更新库存数量
        int newCount = currentCount - num;
        if (newCount == 0) {
            // 如果数量为 0，则从 Map 中移除该 Material
            materials.remove(material);
        } else {
            // 否则更新数量
            materials.put(material, newCount);
        }

        // 更新 Inventory 的 materials 属性（可选，视实现而定）
        inventory.setMaterials(materials);

        // 返回更新后的 Inventory
        return inventory;
    }

    @Override
    @Transactional
    public void Move(String fromId, String toId,String materialId,int num) {
        InventoryAddProduct(toId,materialId,num);
        InventoryRemoveProduct(fromId,materialId,num);
    }
}
