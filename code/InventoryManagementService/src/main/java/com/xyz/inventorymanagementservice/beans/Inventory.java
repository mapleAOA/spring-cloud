package com.xyz.inventorymanagementservice.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    private String id;
    private Map<Material, Integer> materials;
}
