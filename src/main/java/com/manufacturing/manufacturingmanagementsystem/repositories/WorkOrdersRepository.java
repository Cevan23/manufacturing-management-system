package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.WorkOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrdersRepository extends JpaRepository<WorkOrdersEntity, Long> {

    @Query("SELECT w FROM WorkOrdersEntity w WHERE w.productManager.id = ?1")
    List<WorkOrdersEntity> findByProductManagerId(Long id);
}
