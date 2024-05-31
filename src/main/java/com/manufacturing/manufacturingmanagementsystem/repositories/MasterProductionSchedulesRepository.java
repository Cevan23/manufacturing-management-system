package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.MasterProductionSchedulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// Author: Pham Van Cao
// this class is used to handle the MasterProductionSchedulesRepository response
@Repository
public interface MasterProductionSchedulesRepository extends JpaRepository<MasterProductionSchedulesEntity, Long> {
    // find by product manager id
    @Query("SELECT m FROM MasterProductionSchedulesEntity m WHERE m.productManager.id = ?1")
    List<MasterProductionSchedulesEntity> findAllByProductManager_Id(Long pmId);
    // find by MPS id
    @Query("SELECT m FROM MasterProductionSchedulesEntity m WHERE m.id = ?1")
    Optional<MasterProductionSchedulesEntity> findByMPSId(Long mpsId);
    // find by in progress
    @Query("SELECT m FROM MasterProductionSchedulesEntity m WHERE m.in_progress = ?1")
    List<MasterProductionSchedulesEntity> findAllByInProgress(Float inProgress);
}
