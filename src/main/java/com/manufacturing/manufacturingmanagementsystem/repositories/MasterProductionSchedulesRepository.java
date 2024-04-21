package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.model.MasterProductionSchedulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterProductionSchedulesRepository extends JpaRepository<MasterProductionSchedulesEntity, Long> {
    // Add custom query methods if needed
}
