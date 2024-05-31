package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.WorkOrderDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the WorkOrderDetailsRepository response
@Repository
public interface WorkOrderDetailsRepository extends JpaRepository<WorkOrderDetailsEntity, WorkOrderDetailEntityId> {
    // delete by work order id
    @Query("SELECT w FROM WorkOrderDetailsEntity w WHERE w.workOrder.id = ?1")
    List<WorkOrderDetailsEntity> findByWorkOrderId(Long id);
    // delete by work order id and master production schedule id
    @Modifying
    @Transactional
    @Query("DELETE FROM WorkOrderDetailsEntity w WHERE w.workOrder.id = ?1 AND w.masterProductionSchedule.id = ?2")
    void deleteByWorkOrderIdAndMasterProductionScheduleId(Long workOrderId, Long masterProductionScheduleId);
    // delete by work order id and master production schedule id and material id
    @Query("SELECT SUM(w.projectedProduction) FROM WorkOrderDetailsEntity w WHERE w.masterProductionSchedule.id = ?1")
    Integer sumProjectedProductionByMasterProductionSchedule(Long masterProductionScheduleId);


}
