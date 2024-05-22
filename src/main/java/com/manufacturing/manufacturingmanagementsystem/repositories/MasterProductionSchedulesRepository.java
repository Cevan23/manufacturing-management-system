package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.MasterProductionSchedulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterProductionSchedulesRepository extends JpaRepository<MasterProductionSchedulesEntity, Long> {
    @Query("SELECT m FROM MasterProductionSchedulesEntity m WHERE m.productManager.id = ?1")
    List<MasterProductionSchedulesEntity> findAllByProductManager_Id(Long pmId);
}
