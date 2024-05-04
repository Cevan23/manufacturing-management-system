package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.MaterialsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.FillterRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.BOM.BOMresponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Material.MaterialResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
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

@RestController
@RequestMapping("/api/BOMs")
public class BOMControllers {

    @Autowired
    private BOMsServices bomsServices;

    @Autowired
    private MaterialsServices materialsServices;

    @Autowired
    private BOMDetailsServices bomDetailsServices;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<?> createBOM(@RequestBody BOMRequest bomRequest) {
        System.out.println("BOM request 1 : " + bomRequest);
        ResponseEntity<?> errorResponse = checkBOMRequest(bomRequest);
        String status = bomRequest.getBOMStatus();
        if (errorResponse != null) {
            return errorResponse;
        } else if (bomsServices.checkIfBOMExists(bomRequest.getBOMName())) {
            String errorMessage = "BOM with name '" + bomRequest.getBOMName() + "' already exists.";
            return ResponseEntity.badRequest().body(errorMessage);
        } else if (!(status.equals("PENDING") || status.equals("CHECK_PRICE") || status.equals("FINISH"))) {
            String errorMessage = "BOM status must be either 'PENDING', 'CHECK_PRICE', or 'FINISH'.";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        var BOMDto = new BOMsDTO();
        System.out.println("BOM request 2: " + bomRequest);
        BOMDto.setProductManagerId(bomRequest.getProductManagerId());
        BOMDto.setName(bomRequest.getBOMName());
        // first create is pending and that control in FE
        BOMDto.setBOMstatus(bomRequest.getBOMStatus());
        BOMDto.setTimeProduction(bomRequest.getTimeProduction());
        BOMDto.setUnit(bomRequest.getUnit());
        BOMDto.setTotalPrice(bomRequest.getTotalPrice());
        BOMDto.setSellPrice(bomRequest.getSellPrice());

        var bom = bomsServices.createBOM(BOMDto);

        List<BOMDetailsDTO> bomDetails = new ArrayList<>();
        var response = createBOMResponse(bom, bomDetails);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<?> updateBOM(@RequestBody BOMRequest bomRequest) {
        ResponseEntity<?> errorResponse = checkBOMRequest(bomRequest);
        String status = bomRequest.getBOMStatus();
        if (errorResponse != null) {
            return errorResponse;
        } else if (bomRequest.getBomDetails() == null) {
            String errorMessage = "BOM details are required.";
            return ResponseEntity.badRequest().body(errorMessage);
        }else if (!(status.equals("PENDING") || status.equals("CHECK_PRICE") || status.equals("FINISH"))) {
            String errorMessage = "BOM status must be either 'PENDING', 'CHECK_PRICE', or 'FINISH'.";
            return ResponseEntity.badRequest().body(errorMessage);
        }


        var bom = bomsServices.findBOMByName(bomRequest.getBOMName());
        if (bom == null) {
            String errorMessage = "BOM with name '" + bomRequest.getBOMName() + "' does not exist.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        var bomDetailsRequest = bomRequest.getBomDetails();
        List<BOMDetailsDTO> bomDetails = new ArrayList<>();
        for (var bomDetailRequest : bomDetailsRequest) {
            var materialRequest = bomDetailRequest.getMaterial();
            var material = materialsServices.findMaterialByName(materialRequest.getMaterialName());
            if (material == null) {
                var materialDto = new MaterialsDTO();
                materialDto.setName(materialRequest.getMaterialName());
                materialDto.setPrice(materialRequest.getMaterialPrice());
                materialDto.setUnit(materialRequest.getMaterialUnit());
                materialDto.setVolume(materialRequest.getMaterialVolume());

                material = materialsServices.createMaterial(materialDto);
            }

            var bomDetailDto = new BOMDetailsDTO();
            bomDetailDto.setBOMId(bom.getId());
            bomDetailDto.setMaterialId(material.getId());
            bomDetailDto.setQuantity(bomDetailRequest.getQuantity());
            bomDetailDto.setTotalUnitPrice(bomDetailRequest.getTotalUnitPrice());
            var bomDetail = bomDetailsServices.createBOMDetails(bomDetailDto);

            bomDetails.add(bomDetail);
        }

        var response = createBOMResponse(bom, bomDetails);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<?> deleteBOM(@PathVariable Long id) {
        if (id == null) {
            String errorMessage = "BOM id is required.";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Boolean isDeleted = bomsServices.deleteBOM(id);
        if (!isDeleted) {
            String errorMessage = "BOM with id '" + id + "' does not exist or could not be deleted.";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<?> getAllBOMs() {

        List<BOMsEntity> boms = bomsServices.getAllBOMs();
        List<BOMresponse> response = new ArrayList<>();
        for (var bom : boms) {
            List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(bom.getId());
            var bomResponse = createBOMResponse(bom, bomDetails);
            response.add(bomResponse);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll/status")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<?> getAllBOMsbyStatus(@RequestBody FillterRequest statusRequest) {
       try {
            if (statusRequest == null) {
                String errorMessage = "Status request is required.";
                return ResponseEntity.badRequest().body(errorMessage);
            } else if (statusRequest.getStatus() == null) {
                String errorMessage = "Status is required.";
                return ResponseEntity.badRequest().body(errorMessage);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);
       }
        List<BOMsEntity> boms = bomsServices.getAllBOMsbyStatus(statusRequest.getStatus());
        List<BOMresponse> response = new ArrayList<>();
        for (var bom : boms) {
            List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(bom.getId());
            var bomResponse = createBOMResponse(bom, bomDetails);
            response.add(bomResponse);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBOMsByName")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<?> getBOMsByName(@RequestBody FillterRequest nameRequest) {
        if (nameRequest == null) {
            String errorMessage = "BOM request is required.";
            return ResponseEntity.badRequest().body(errorMessage);
        } else if (nameRequest.getName() == null) {
            String errorMessage = "BOM name is required.";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        var bom = bomsServices.findBOMByName(nameRequest.getName());
        if (bom == null) {
            String errorMessage = "BOM with name '" + nameRequest.getName() + "' does not exist.";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(bom.getId());
        var response = createBOMResponse(bom, bomDetails);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBOMsByLikeName")
    @PreAuthorize("hasAnyAuthority('MANAGER_BOM')")
    public ResponseEntity<?> getBOMsByLikeName(@RequestBody FillterRequest nameRequest) {
        if (nameRequest == null) {
            String errorMessage = "BOM request is required.";
            return ResponseEntity.badRequest().body(errorMessage);
        } else if (nameRequest.getName() == null) {
            String errorMessage = "BOM name is required.";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        List<BOMsEntity> boms = bomsServices.getBOMsByLikeName(nameRequest.getName());
        List<BOMresponse> response = new ArrayList<>();
        for (var bom : boms) {
            List<BOMDetailsDTO> bomDetails = bomDetailsServices.getBOMDetailsByBOMId(bom.getId());
            var bomResponse = createBOMResponse(bom, bomDetails);
            response.add(bomResponse);
        }

        return ResponseEntity.ok(response);
    }

    private ResponseEntity<?> checkBOMRequest(BOMRequest bomRequest) {
        if (bomRequest == null) {
            return ResponseEntity.badRequest().build();
        } else if (bomRequest.getBOMName() == null) {
            String errorMessage = "BOM name is required.";
            return ResponseEntity.badRequest().body(errorMessage);
        } else if (bomRequest.getBOMStatus() == null) {
            String errorMessage = "BOM status is required.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return null;
    }

    private BOMresponse createBOMResponse(BOMsEntity bom, List<BOMDetailsDTO> bomDetails) {
        var response = new BOMresponse();
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
