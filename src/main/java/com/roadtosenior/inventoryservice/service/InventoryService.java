package com.roadtosenior.inventoryservice.service;

import com.roadtosenior.inventoryservice.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    public InventoryDto save(InventoryDto inventoryDto);
    public List<InventoryDto> findAll();
    public InventoryDto findOne(Long id);
    public List<InventoryDto> getStock(List<String> skuCode);
}
