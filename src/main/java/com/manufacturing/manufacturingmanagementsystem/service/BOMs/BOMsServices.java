package com.manufacturing.manufacturingmanagementsystem.service.BOMs;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BOMsServices implements IBOMsServices {

    private final BOMsRepository bomsRepository;

    @Override
    public BOMsDTO createBOM(BOMsDTO bomRequest) {
        return null;
    }

    public boolean checkIfBOMExists(String name) {
        return bomsRepository.findByName(name) != null;
    }
}

