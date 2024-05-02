package com.manufacturing.manufacturingmanagementsystem.service.BOMs;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMsRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Users.UsersServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BOMsServices implements IBOMsServices {

    private final BOMsRepository bomsRepository;
    private UsersServices usersServices;
    @Override
    public BOMsEntity createBOM(BOMsDTO bomRequest) {

        BOMsEntity bomsEntity = new BOMsEntity();
        if(usersServices.getUserById(bomRequest.getProductManagerId()) == null) {
            throw new RuntimeException("User does not exist");
        }

        bomsEntity.setProductManager(usersServices.getUserById(bomRequest.getProductManagerId()));
        bomsEntity.setName(bomRequest.getName());
        bomsEntity.setBOMstatus(bomRequest.getBOMstatus());
        bomsEntity.setDateCreation(java.sql.Date.valueOf(LocalDate.now()));
        bomsEntity.setTimeProduction(bomRequest.getTimeProduction());
        bomsEntity.setUnit(bomRequest.getUnit());
        bomsEntity.setTotalPrice(bomRequest.getTotalPrice());
        bomsEntity.setSellPrice(bomRequest.getSellPrice());

        // Save BOMsEntity to database
        bomsEntity = bomsRepository.save(bomsEntity);

        return bomsEntity;
    }

    @Override
    public BOMsEntity findBOMByName(String name) {
        return bomsRepository.findByName(name);
    }

    public boolean checkIfBOMExists(String name) {
        return bomsRepository.findByName(name) != null;
    }
}

