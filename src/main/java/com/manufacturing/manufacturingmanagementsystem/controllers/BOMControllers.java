package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.MaterialsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.FillterRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.BOM.BOMResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Material.MaterialResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import com.manufacturing.manufacturingmanagementsystem.service.BOMDetails.BOMDetailsServices;
import com.manufacturing.manufacturingmanagementsystem.service.BOMs.BOMsServices;
import com.manufacturing.manufacturingmanagementsystem.service.Materials.MaterialsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/BOMs")
public class BOMControllers {

    private final BOMsServices bomsServices;
    private final MaterialsServices materialsServices;
    private final BOMDetailsServices bomDetailsServices;

    @Autowired
    public BOMControllers(BOMsServices bomsServices, MaterialsServices materialsServices, BOMDetailsServices bomDetailsServices) {
        this.bomsServices = bomsServices;
        this.materialsServices = materialsServices;
        this.bomDetailsServices = bomDetailsServices;
    }

    @PostMapping("/createBOMs")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> createBOMs(@RequestBody List<BOMRequest> bomRequests) {

        if(bomRequests == null || bomRequests.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("BOMs request is required.")
                            .result(null)
                            .build());
        }
        try{
            for (BOMRequest bomRequest : bomRequests) {
                bomsServices.createBOM(bomRequest);
                for (BOMDetailRequest bomDetailRequest : bomRequest.getBomDetails()) {
                    bomDetailsServices.createBOMDetails(bomDetailRequest);
                }
            }

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("BOMs created successfully")
                            .result(null)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOMs could not be created. " + e.getMessage())
                            .result(null)
                            .build());
        }
    }

    @PutMapping("/updateBOM/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> updateBOM( @PathVariable Long id ,@RequestBody BOMRequest bomRequest) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("The given id must not be null")
                            .result(null)
                            .build());
        }
        try {
            try {
                System.out.println("BOM update id : " + bomRequest + " id : " + id);
                bomsServices.updateBOM(bomRequest, id);
            }catch (Exception e){
                return ResponseEntity.badRequest()
                        .body(ApiResponse.builder()
                                .code(ErrorCode.BAD_REQUEST.getCode())
                                .message("BOM update have problem "+ e.getMessage())
                                .result(null)
                                .build());
            }

            try {
                for (BOMDetailRequest bomDetailRequest : bomRequest.getBomDetails()) {
                    bomDetailRequest.setBOMId(id);
                    var material = materialsServices.findMaterialByName(bomDetailRequest.getMaterial().getMaterialName());

                    if (material == null || material.getReuseId() == null) {
                        materialsServices.createMaterial(MaterialsDTO.builder()
                                .name(bomDetailRequest.getMaterial().getMaterialName())
                                .price(bomDetailRequest.getMaterial().getMaterialPrice())
                                .unit(bomDetailRequest.getMaterial().getMaterialUnit())
                                .volume(bomDetailRequest.getMaterial().getMaterialVolume())
                                .build());
                        bomDetailsServices.createBOMDetails(bomDetailRequest);
                    } else {
                        bomDetailsServices.createBOMDetails(bomDetailRequest);

                    }
                }
            }catch (Exception e){
                return ResponseEntity.badRequest()
                        .body(ApiResponse.builder()
                                .code(ErrorCode.BAD_REQUEST.getCode())
                                .message("BOM detail update have problem "+ e.getMessage())
                                .result(null)
                                .build());
            }
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("BOM updated successfully")
                            .result(null)
                            .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("BOM with name '" + bomRequest.getBOMName() + "' does not exist." + e.getMessage())
                            .result(null)
                            .build());
        }


    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> deleteBOM(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM id is required.")
                            .result(null)
                            .build());
        }

        Boolean isDeleted = bomsServices.deleteBOM(id);
        if (!isDeleted) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM with id '" + id + "' does not exist or could not be deleted.")
                            .result(null)
                            .build());
        }

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("BOM deleted successfully")
                        .result(null)
                        .build());
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> getAllBOMs() {

        List<BOMsEntity> boms = bomsServices.getAllBOMs();
        List<BOMResponse> response = new ArrayList<>();
        for (var bom : boms) {
            List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(bom.getId());
            var bomResponse = createBOMResponse(bom, bomDetails);
            response.add(bomResponse);
        }

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("BOMs fetched successfully")
                        .result(response)
                        .build());
    }

    @GetMapping("/getAll/status")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> getAllBOMsbyStatus(@RequestBody FillterRequest statusRequest) {
        if (statusRequest == null || statusRequest.getStatus() == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("Status request is required.")
                            .result(null)
                            .build());
        }

        List<BOMsEntity> boms = bomsServices.getAllBOMsbyStatus(statusRequest.getStatus());
        List<BOMResponse> response = new ArrayList<>();
        for (var bom : boms) {
            List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(bom.getId());
            var bomResponse = createBOMResponse(bom, bomDetails);
            response.add(bomResponse);
        }

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("BOMs fetched successfully by status")
                        .result(response)
                        .build());
    }

    @GetMapping("/getBOMsByName")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> getBOMsByName(@RequestBody FillterRequest nameRequest) {
        if (nameRequest == null || nameRequest.getName() == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM name is required.")
                            .result(null)
                            .build());
        }

        var bom = bomsServices.findBOMByName(nameRequest.getName());
        if (bom == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM with name '" + nameRequest.getName() + "' does not exist.")
                            .result(null)
                            .build());
        }

        List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(bom.getId());
        var response = createBOMResponse(bom, bomDetails);

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("BOM fetched successfully by name")
                        .result(response)
                        .build());
    }

    @GetMapping("/getBOMsByLikeName")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> getBOMsByLikeName(@RequestBody FillterRequest nameRequest) {
        if (nameRequest == null || nameRequest.getName() == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM name is required.")
                            .result(null)
                            .build());
        }

        List<BOMsEntity> boms = bomsServices.getBOMsByLikeName(nameRequest.getName());
        List<BOMResponse> response = new ArrayList<>();
        for (var bom : boms) {
            List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(bom.getId());
            var bomResponse = createBOMResponse(bom, bomDetails);
            response.add(bomResponse);
        }

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("BOMs fetched successfully by like name")
                        .result(response)
                        .build());
    }

    @GetMapping("/getBOMById/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> getBOMById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM id is required.")
                            .result(null)
                            .build());
        }
        try {
            var bom = bomsServices.findBOMById(id);
            if (bom == null) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.builder()
                                .message("BOM with id '" + id + "' does not exist.")
                                .result(null)
                                .build());
            }

            List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(id);

            var response = createBOMResponse(bom, bomDetails);

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("BOM fetched successfully by id")
                            .result(response)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM with id can not get")
                            .result(null)
                            .build());
        }
    }
    private BOMResponse createBOMResponse(BOMsEntity bom, List<BOMDetailsDTO> bomDetails) {
        var response = new BOMResponse();
        response.setBOMName(bom.getName());
        response.setBOMStatus(bom.getBOMstatus());
        response.setTimeProduction(bom.getTimeProduction());
        response.setUnit(bom.getUnit());
        response.setTotalPrice(bom.getTotalPrice());
        response.setSellPrice(bom.getSellPrice());
        response.setDateCreation(bom.getDateCreation().toString());
        var productManager = new UserResponse();
        productManager.setFullName(bom.getProductManager().getFullName());
        productManager.setEmail(bom.getProductManager().getEmail());
        response.setProductManager(productManager);

        List<MaterialResponse> materials = new ArrayList<>();
        for (var bomDetail : bomDetails) {
            var material = materialsServices.findMaterialById(bomDetail.getMaterialId());
            var materialResponse = new MaterialResponse();
            materialResponse.setMaterialName(material.getName());
            materialResponse.setMaterialPrice(material.getPrice());
            materialResponse.setMaterialUnit(material.getUnit());
            materialResponse.setMaterialVolume(material.getVolume());
            materials.add(materialResponse);
        }
        response.setMaterials(materials);

        return response;
    }

}
