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
// Author: Pham Van Cao
// create BOM, update BOM, delete BOM, get all BOMs, get BOMs by status, get BOMs by name, get BOMs by like name, get BOM by id, delete BOM detail
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
    // Author: Pham Van Cao
    // endpoint to create a new BOM
    @PostMapping("/createBOMs")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> createBOMs(@RequestBody BOMRequest bomRequest) {

        if(bomRequest == null ) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("BOMs request is required.")
                            .result(null)
                            .build());
        }
        try{
                BOMsEntity createdBOM = bomsServices.createBOM(bomRequest);
                Long createdBOMId = createdBOM.getId();
                if(bomRequest.getBomDetails() != null){
                    try {
                        List<BOMDetailsDTO> oldBomDetails = bomDetailsServices.getBOMDetailsByBOMId(createdBOMId);
                        for (BOMDetailRequest bomDetailRequest : bomRequest.getBomDetails()) {
                            bomDetailRequest.setBOMId(createdBOMId);

                            String newMaterialName = bomDetailRequest.getMaterial().getMaterialName();

                            Optional<BOMDetailsDTO> oldBomDetailOpt = oldBomDetails.stream()
                                    .filter(b -> {
                                        MaterialsDTO oldMaterial = materialsServices.findMaterialById(b.getMaterialId());
                                        return oldMaterial != null && oldMaterial.getName().equals(newMaterialName);
                                    })
                                    .findFirst();
                            if (oldBomDetailOpt.isPresent()) {
                                // Nếu bomDetail cũ tồn tại, so sánh và cập nhật nếu cần
                                BOMDetailsDTO oldBomDetail = oldBomDetailOpt.get();
                                if (!oldBomDetail.getQuantity().equals(bomDetailRequest.getQuantity()) ||
                                        !oldBomDetail.getTotalUnitPrice().equals(bomDetailRequest.getTotalUnitPrice())) {
                                    // Cập nhật bomDetail nếu có sự khác biệt
//                            bomDetailsServices.updateBOMDetails(bomDetailRequest);
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
                            } else {
                                // Nếu bomDetail cũ không tồn tại, thêm newBomDetail vào cơ sở dữ liệu
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

                            for (BOMDetailsDTO oldBomDetail : oldBomDetails) {
                                MaterialsDTO oldMaterial = materialsServices.findMaterialById(oldBomDetail.getMaterialId());
                                if (oldMaterial != null && bomRequest.getBomDetails().stream().noneMatch(b -> b.getMaterial().getMaterialName().equals(oldMaterial.getName()))) {
                                    bomDetailsServices.deleteBOMDetail(createdBOMId, oldBomDetail.getMaterialId());
                                }
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
    // Author: Pham Van Cao
    // endpoint to update a BOM
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
                System.out.println("BOM update id controller : " + bomRequest + " id : " + id);
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
                List<BOMDetailsDTO> oldBomDetails = bomDetailsServices.getBOMDetailsByBOMId(id);
                for (BOMDetailRequest bomDetailRequest : bomRequest.getBomDetails()) {
                    bomDetailRequest.setBOMId(id);

                    String newMaterialName = bomDetailRequest.getMaterial().getMaterialName();

                    Optional<BOMDetailsDTO> oldBomDetailOpt = oldBomDetails.stream()
                            .filter(b -> {
                                MaterialsDTO oldMaterial = materialsServices.findMaterialById(b.getMaterialId());
                                return oldMaterial != null && oldMaterial.getName().equals(newMaterialName);
                            })
                            .findFirst();
                    if (oldBomDetailOpt.isPresent()) {
                        // Nếu bomDetail cũ tồn tại, so sánh và cập nhật nếu cần
                        BOMDetailsDTO oldBomDetail = oldBomDetailOpt.get();
                        if (!oldBomDetail.getQuantity().equals(bomDetailRequest.getQuantity()) ||
                                !oldBomDetail.getTotalUnitPrice().equals(bomDetailRequest.getTotalUnitPrice())) {
                            // Cập nhật bomDetail nếu có sự khác biệt
//                            bomDetailsServices.updateBOMDetails(bomDetailRequest);
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
                    } else {
                        // Nếu bomDetail cũ không tồn tại, thêm newBomDetail vào cơ sở dữ liệu
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

                    for (BOMDetailsDTO oldBomDetail : oldBomDetails) {
                        MaterialsDTO oldMaterial = materialsServices.findMaterialById(oldBomDetail.getMaterialId());
                        if (oldMaterial != null && bomRequest.getBomDetails().stream().noneMatch(b -> b.getMaterial().getMaterialName().equals(oldMaterial.getName()))) {
                            bomDetailsServices.deleteBOMDetail(id, oldBomDetail.getMaterialId());
                        }
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
    // Author: Pham Van Cao
    // endpoint to delete a BOM
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
    // Author: Pham Van Cao
    // endpoint to get all BOMs
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
    // Author: Pham Van Cao
    // endpoint to get all BOMs by status
    @GetMapping("/getAll/status")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> getAllBOMsbyStatus(@RequestParam String statusRequest) {
        if (statusRequest == null ) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("Status request is required.")
                            .result(null)
                            .build());
        }

        List<BOMsEntity> boms = bomsServices.getAllBOMsbyStatus(statusRequest);
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
    // Author: Pham Van Cao
    // endpoint to get all BOMs by name
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
    // Author: Pham Van Cao
    // endpoint to get all BOMs by like name
    @GetMapping("/getBOMsByLikeName")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> getBOMsByLikeName(@RequestParam String nameRequest) {
        if (nameRequest == null ) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM name is required.")
                            .result(null)
                            .build());
        }

        List<BOMsEntity> boms = bomsServices.getBOMsByLikeName(nameRequest);
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
    // Author: Pham Van Cao
    // endpoint to get a BOM by id
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
    // Author: Pham Van Cao
    // endpoint to delete a BOM detail
    @DeleteMapping("/deleteBOMDetail/{bomId}/{materialId}")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<ApiResponse> deleteBOMDetail(@PathVariable Long bomId, @PathVariable Long materialId) {
        if (bomId == null || materialId == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM id and material id are required.")
                            .result(null)
                            .build());
        }
        try {
            System.out.println("BOM id : " + bomId + " material id : " + materialId);
            bomDetailsServices.deleteBOMDetail(bomId, materialId);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("BOM detail with BOM id '" + bomId + "' and material id '" + materialId + "' does not exist or could not be deleted.")
                            .result(null)
                            .build());
        }



        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("BOM detail deleted successfully")
                        .result(null)
                        .build());
    }
    // Author: Pham Van Cao
    // create BOM response
    private BOMResponse createBOMResponse(BOMsEntity bom, List<BOMDetailsDTO> bomDetails) {
        var response = new BOMResponse();
        response.setId(bom.getId());
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
            materialResponse.setMaterialId(bomDetail.getMaterialId());
            materialResponse.setMaterialName(material.getName());
            materialResponse.setMaterialPrice(material.getPrice());
            materialResponse.setMaterialUnit(material.getUnit());
            materialResponse.setMaterialVolume(material.getVolume());
            materialResponse.setMaterialQuantity(bomDetail.getQuantity());
            materials.add(materialResponse);
        }
        response.setMaterials(materials);

        return response;
    }

}
