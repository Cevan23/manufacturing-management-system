package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.WorkOrderDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderDetailsRepository extends JpaRepository<WorkOrderDetailsEntity, WorkOrderDetailEntityId> {

    @Query("SELECT w FROM WorkOrderDetailsEntity w WHERE w.workOrder.id = ?1")
    List<WorkOrderDetailsEntity> findByWorkOrderId(Long id);

}
