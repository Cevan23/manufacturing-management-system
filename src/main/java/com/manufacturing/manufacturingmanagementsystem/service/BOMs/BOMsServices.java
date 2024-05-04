package com.manufacturing.manufacturingmanagementsystem.service.BOMs;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMsRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Users.UsersServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BOMsServices implements IBOMsServices {

    private final BOMsRepository bomsRepository;
    private final BOMDetailsRepository bomDetailsRepository;
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
    public Boolean deleteBOM(Long id) {
        Optional<BOMsEntity> bomsEntity = bomsRepository.findById(id);

        if (bomsEntity.isEmpty()) {
            return false;
        }
        try {
            bomDetailsRepository.deleteByBOMId(id);
            bomsRepository.delete(bomsEntity.get());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public BOMsEntity findBOMByName(String name) {
        return bomsRepository.findByName(name);
    }

    @Override
    public List<BOMsEntity> getBOMsByLikeName(String name) {
        return bomsRepository.findByNameLike(name);
    }

    @Override
    public List<BOMsEntity> getAllBOMs() {
        try {
            return bomsRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<BOMsEntity> getAllBOMsbyStatus(String status) {
        try {
            return bomsRepository.findByBOMstatus(status);
        } catch (Exception e) {
            return null;

        }
    }

    public boolean checkIfBOMExists(String name) {
        return bomsRepository.findByName(name) != null;
    }
}

