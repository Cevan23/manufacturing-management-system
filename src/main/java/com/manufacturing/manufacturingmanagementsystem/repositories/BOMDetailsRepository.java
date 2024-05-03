package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.BOMDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.BOMDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BOMDetailsRepository extends JpaRepository<BOMDetailsEntity, BOMDetailEntityId> {

        void deleteByBOMId(Long bomId);

        List<BOMDetailsEntity> findByBOMId(Long bomId);
}
