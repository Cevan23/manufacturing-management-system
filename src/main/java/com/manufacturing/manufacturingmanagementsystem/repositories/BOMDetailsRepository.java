package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.BOMDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.BOMDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the BOMDetailsRepository response
@Repository
public interface BOMDetailsRepository extends JpaRepository<BOMDetailsEntity, BOMDetailEntityId> {
        // delete by BOM id
        @Modifying
        @Transactional
        @Query("DELETE FROM BOMDetailsEntity b WHERE b.BOM.id = ?1")
        void deleteByBOMId(Long bomId);
        // delete by BOM id and material id
        @Modifying
        @Transactional
        @Query("DELETE FROM BOMDetailsEntity b WHERE b.BOM.id = ?1 AND b.material.id = ?2")
        void deleteByBOMIdAndMaterialId(Long bomId, Long materialId);
        // find by BOM id
        List<BOMDetailsEntity> findByBOMId(Long bomId);
}
