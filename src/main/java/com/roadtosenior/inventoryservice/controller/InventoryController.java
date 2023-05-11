package com.roadtosenior.inventoryservice.controller;

import com.roadtosenior.inventoryservice.dto.InventoryDto;
import com.roadtosenior.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDto> getInventories(){
        return inventoryService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryDto getInventory(@PathVariable Long id){
        return inventoryService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryDto createInventory(@Valid @RequestBody InventoryDto inventoryDto){
        return inventoryService.save(inventoryDto);
    }

    @GetMapping(path = "/stock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDto> getStock(@RequestParam List<String> skuCode){
        return inventoryService.getStock(skuCode);
    }
}
