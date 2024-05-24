package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.WorkOrderDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WorkOrderDetailsRepository extends JpaRepository<WorkOrderDetailsEntity, WorkOrderDetailEntityId> {

    @Query("SELECT w FROM WorkOrderDetailsEntity w WHERE w.workOrder.id = ?1")
    List<WorkOrderDetailsEntity> findByWorkOrderId(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM WorkOrderDetailsEntity w WHERE w.workOrder.id = ?1 AND w.masterProductionSchedule.id = ?2")
    void deleteByWorkOrderIdAndMasterProductionScheduleId(Long workOrderId, Long masterProductionScheduleId);

}
