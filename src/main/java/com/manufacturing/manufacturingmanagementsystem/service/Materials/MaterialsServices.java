package com.manufacturing.manufacturingmanagementsystem.service.Materials;

import com.manufacturing.manufacturingmanagementsystem.dtos.MaterialsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.MaterialsRepository;
import com.manufacturing.manufacturingmanagementsystem.service.OrderMaterialDetails.OrderMaterialDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
// Author: Pham Hien Nhan
// this class is used to handle the MaterialsServices response
@Service
@AllArgsConstructor
public class MaterialsServices implements IMaterialsServices {

    private final MaterialsRepository materialsRepository;
    private final OrderMaterialDetailsServices orderMaterialDetailsServices;
    // this service is used to create Material
    @Override
    public void createMaterial(MaterialsDTO materialDto)  {
        var materialEntity = materialsRepository.findByName(materialDto.getName());
        if (materialEntity.isPresent()) {
            return;
        }
        // Convert DTO to entity
        MaterialsEntity material = new MaterialsEntity();
        material.setName(materialDto.getName());
        material.setPrice(materialDto.getPrice());
        material.setUnit(materialDto.getUnit());
        material.setVolume(materialDto.getVolume());
        System.out.println("Material create : " + material);
        // Save entity in the database
        materialsRepository.save(material);
    }
    // this service is used to update Material
    @Override
    public void updateMaterial(MaterialsDTO materialDto)  {
            Optional<MaterialsEntity> material = materialsRepository.findById(materialDto.getId());
            var materialEntity = materialsRepository.findByName(materialDto.getName());
            if (material.isPresent() && materialEntity.isEmpty()) {
                material.get().setName(materialDto.getName());
                material.get().setPrice(materialDto.getPrice());
                material.get().setUnit(materialDto.getUnit());
                material.get().setVolume(materialDto.getVolume());
                materialsRepository.save(material.get());
            }

    }
    // this service is used to delete Material
    @Override
    public void deleteMaterial(Long id)  {
        materialsRepository.deleteById(id);
    }
    // this service is used to get all Materials
    @Override
    public List<MaterialsEntity> findAllMaterials() {
        return materialsRepository.findAll();
    }
    // this service is used to get all Materials by name
    @Override
    public List<MaterialsEntity> findAlikeName(String name){
        if(name == null ){
            return null;
        }
        Optional<List<MaterialsEntity>> materials = materialsRepository.findByNameContaining(name);

        return materials.orElse(null);
    }
    // this service is used to get Material by name
    @Override
    public MaterialsEntity findMaterialByName(String name){
        return materialsRepository.findByName(name).orElse(null);
    }
    // this service is used to get Material by id
    @Override
    public MaterialsDTO findMaterialById(Long id){
        MaterialsEntity materialEntity = materialsRepository.findById(id).orElse(null);
        if (materialEntity == null) {
            return null;
        }

        // Convert entity to DTO
        MaterialsDTO materialDto = new MaterialsDTO();
        materialDto.setName(materialEntity.getName());
        materialDto.setPrice(materialEntity.getPrice());
        materialDto.setUnit(materialEntity.getUnit());
        materialDto.setVolume(materialEntity.getVolume());

        return materialDto;
    }
    // this service is used to get Material for OrderMaterial by id
    @Override
    public List<Map<String, Object>> getMaterialForOrderMaterialById(Long id) {
        List<MaterialsEntity> materialsEntities = materialsRepository.findAll();
        List<Map<String, Object>> listOrderMaterialDetail = orderMaterialDetailsServices.findOrderMaterialDetailById(id);
        List<Map<String, Object>> materialMap = new ArrayList<>();
        if (listOrderMaterialDetail.isEmpty()) {
            for (MaterialsEntity material : materialsEntities) {
                Map<String, Object> materialInfo = new HashMap<>();
                materialInfo.put("id", material.getId());
                materialInfo.put("name", material.getName());
                materialInfo.put("price", material.getPrice());
                materialMap.add(materialInfo);
            }
        } else {
            Set<Long> ordermaterialDetailmaterialIds = listOrderMaterialDetail.stream()
                    .map(detail -> (Long) detail.get("material_id"))
                    .collect(Collectors.toSet());
            for (MaterialsEntity material : materialsEntities) {
                if (!ordermaterialDetailmaterialIds.contains(material.getId())) {
                    Map<String, Object> materialInfo = new HashMap<>();
                    materialInfo.put("id", material.getId());
                    materialInfo.put("name", material.getName());
                    materialInfo.put("price", material.getPrice());
                    materialMap.add(materialInfo);
                }
            }
        }
        return materialMap;
    }

}

