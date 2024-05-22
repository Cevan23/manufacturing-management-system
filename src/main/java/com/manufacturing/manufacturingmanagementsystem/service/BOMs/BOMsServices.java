package com.manufacturing.manufacturingmanagementsystem.service.BOMs;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.BOM.BOMResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Material.MaterialResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMsRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Materials.MaterialsServices;
import com.manufacturing.manufacturingmanagementsystem.service.Users.UsersServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BOMsServices implements IBOMsServices {

    private final BOMsRepository bomsRepository;
    private final BOMDetailsRepository bomDetailsRepository;
    private UsersServices usersServices;
    private final MaterialsServices materialsServices;

    @Override
    public void createBOM(BOMRequest bomRequest) {

        String status = bomRequest.getBOMStatus();
        if (checkIfBOMExists(bomRequest.getBOMName())) {
            throw new AppException(ErrorCode.BOM_NOT_FOUND);
        } else if (!(status.equals("PENDING") || status.equals("CHECK_PRICE") || status.equals("FINISH"))) {
            throw new AppException(ErrorCode.STATUS_INCORRECT);
        }

        BOMsEntity bom = new BOMsEntity();

        try {
            bom.setName(bomRequest.getBOMName());
            bom.setBOMstatus(bomRequest.getBOMStatus());
            bom.setTimeProduction(bomRequest.getTimeProduction());
            bom.setUnit(bomRequest.getUnit());
            bom.setTotalPrice(bomRequest.getTotalPrice());
            bom.setSellPrice(bomRequest.getSellPrice());
            bom.setProductManager(usersServices.getUserById(bomRequest.getProductManagerId()));
            bom.setDateCreation(new Date(System.currentTimeMillis()) );
            bomsRepository.save(bom);
        } catch (Exception e) {

            throw new AppException(ErrorCode.BAD_REQUEST);
        }


    }

    @Override
    @Transactional
    public void updateBOM(BOMRequest bomRequest, Long id) {
        String status = bomRequest.getBOMStatus();
        if (!(status.equals("PENDING") || status.equals("CHECK_PRICE") || status.equals("FINISH"))) {
            throw new AppException(ErrorCode.STATUS_INCORRECT);
        }
        try {
            Optional<BOMsEntity> bom = bomsRepository.findById(id);
            System.out.println("BOM update Service : " + bom);
            if (bom.isEmpty()) {
                throw new AppException(ErrorCode.BOM_NOT_FOUND);
            }
            Optional<UsersEntity> user = Optional.ofNullable(usersServices.getUserById(bomRequest.getProductManagerId()));
            if (user.isEmpty()) {
                throw new AppException(ErrorCode.USER_NOT_FOUND);
            }
            bom.get().setName(bomRequest.getBOMName());
            bom.get().setBOMstatus(bomRequest.getBOMStatus());
            bom.get().setTimeProduction(bomRequest.getTimeProduction());
            bom.get().setUnit(bomRequest.getUnit());
            bom.get().setTotalPrice(bomRequest.getTotalPrice());
            bom.get().setSellPrice(bomRequest.getSellPrice());
            bom.get().setProductManager(user.get());
            bomsRepository.save(bom.get());
        } catch (Exception e) {
            System.out.println("BOM not found" + e);
            throw new AppException(ErrorCode.BOM_NOT_FOUND);
        }


    }

    @Override
    public BOMsDTO getBOMById(Long id) {
        Optional<BOMsEntity> bom = bomsRepository.findById(id);
        if (bom.isEmpty()) {
            throw new AppException(ErrorCode.BOM_NOT_FOUND);
        }
        return BOMsDTO.builder()
                .name(bom.get().getName())
                .BOMstatus(bom.get().getBOMstatus())
                .dateCreation(bom.get().getDateCreation())
                .timeProduction(bom.get().getTimeProduction())
                .unit(bom.get().getUnit())
                .totalPrice(bom.get().getTotalPrice())
                .sellPrice(bom.get().getSellPrice())
                .build();
    }

    @Override
    public BOMsEntity findBOMById(Long id) {
        Optional<BOMsEntity> bom = bomsRepository.findById(id);
        if (bom.isEmpty()) {
            throw new AppException(ErrorCode.BOM_NOT_FOUND);
        }
        return bom.get();
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

        Optional<BOMsEntity> bom = bomsRepository.findByName(name);
        if (bom.isEmpty()) {
            throw new AppException(ErrorCode.BOM_NOT_FOUND);
        }
        return bom.get();
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
        Optional<BOMsEntity> bom = bomsRepository.findByName(name);
        System.out.println("check : " + bom.isPresent() + bom);
        return bom.isPresent();
    }

}

