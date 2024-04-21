package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.BOMDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.BOMDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BOMDetailsRepository extends JpaRepository<BOMDetailsEntity, BOMDetailEntityId> {
    // Add custom query methods if needed
}
