package com.roadtosenior.inventoryservice.service.impl;

import com.roadtosenior.inventoryservice.dto.InventoryDto;
import com.roadtosenior.inventoryservice.mapper.InventoryMapper;
import com.roadtosenior.inventoryservice.model.Inventory;
import com.roadtosenior.inventoryservice.repository.InventoryRepository;
import com.roadtosenior.inventoryservice.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryServiceImpl (InventoryRepository inventoryRepository, InventoryMapper inventoryMapper){
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    @Transactional
    public InventoryDto save(InventoryDto inventoryDto){
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryDto.getSkuCode());
        inventory.setQuantity(inventoryDto.getQuantity());
        inventoryRepository.save(inventory);
        return inventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDto> findAll(){
        List<Inventory> inventoryList = inventoryRepository.findAll();
        if (!inventoryList.isEmpty()) {
            return inventoryMapper.toDTO(inventoryList);
        } else throw new NoSuchElementException();
    }

    @Override
    public InventoryDto findOne(Long id){
        if (id == null){
            throw new InvalidParameterException();
        }
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return inventoryMapper.toDTO(inventory);
    }

    @Override
    @Transactional
    public List<InventoryDto> getStock(List<String> skuCodesList) {
        if (skuCodesList.isEmpty()) {
            throw new NoSuchElementException();
        }
        List<InventoryDto> inventoryDtoList = new LinkedList<>();
        for (String skuCode : skuCodesList) {
            Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(NoSuchElementException::new);
            inventoryDtoList.add(inventoryMapper.toDTO(inventory));
        }
        return inventoryDtoList;
    }
}
