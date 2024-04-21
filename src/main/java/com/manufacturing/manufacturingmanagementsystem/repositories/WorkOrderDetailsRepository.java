package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.WorkOrderDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderDetailsRepository extends JpaRepository<WorkOrderDetailsEntity, WorkOrderDetailEntityId> {
    // Add custom query methods if needed
}
