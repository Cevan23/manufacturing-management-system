package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.BOMDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.BOMDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BOMDetailsRepository extends JpaRepository<BOMDetailsEntity, BOMDetailEntityId> {

        @Modifying
        @Transactional
        @Query("DELETE FROM BOMDetailsEntity b WHERE b.BOM.id = ?1")
        void deleteByBOMId(Long bomId);

        @Modifying
        @Transactional
        @Query("DELETE FROM BOMDetailsEntity b WHERE b.BOM.id = ?1 AND b.material.id = ?2")
        void deleteByBOMIdAndMaterialId(Long bomId, Long materialId);

        List<BOMDetailsEntity> findByBOMId(Long bomId);
}
