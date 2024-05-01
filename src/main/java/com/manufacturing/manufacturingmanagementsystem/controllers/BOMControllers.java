package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.BOM.BOMresponse;
import com.manufacturing.manufacturingmanagementsystem.service.BOMs.BOMsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class BOMControllers {

    @Autowired
    private BOMsServices bomsServices;

    @PostMapping("/create")
    public ResponseEntity<?> createBOM(@RequestBody BOMRequest bomRequest) {
        if (bomRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        else if(bomsServices.checkIfBOMExists(bomRequest.getBOMName())) {
            String errorMessage = "BOM with name '" + bomRequest.getBOMName() + "' already exists.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        var BOMDto = new BOMsDTO();

        BOMDto.setProductManagerId(bomRequest.getProductManagerId());
        BOMDto.setName(bomRequest.getBOMName());
        BOMDto.setBOMstatus(bomRequest.getBOMStatus());
        BOMDto.setTimeProduction(bomRequest.getTimeProduction());
        BOMDto.setUnit(bomRequest.getUnit());
        BOMDto.setTotalPrice(bomRequest.getTotalPrice());
        BOMDto.setSellPrice(bomRequest.getSellPrice());

        var bom = bomsServices.createBOM(BOMDto);

        var response = new BOMresponse();
        return ResponseEntity.ok(response);

    }
}
