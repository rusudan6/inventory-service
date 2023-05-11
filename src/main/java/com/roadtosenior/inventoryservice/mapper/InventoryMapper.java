package com.roadtosenior.inventoryservice.mapper;

import com.roadtosenior.inventoryservice.model.Inventory;
import com.roadtosenior.inventoryservice.dto.InventoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class InventoryMapper {
    public abstract Inventory toEntity(InventoryDto inventoryDTO);
    public abstract InventoryDto toDTO(Inventory inventory);
    public abstract List<Inventory> toEntity(List<InventoryDto> inventoryDtoList);
    public abstract List<InventoryDto> toDTO(List<Inventory> inventoryList);
}
