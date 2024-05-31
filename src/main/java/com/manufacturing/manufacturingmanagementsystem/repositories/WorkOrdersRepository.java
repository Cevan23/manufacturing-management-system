package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.WorkOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the WorkOrdersRepository response
@Repository
public interface WorkOrdersRepository extends JpaRepository<WorkOrdersEntity, Long> {

    // find by product manager id
    @Query("SELECT w FROM WorkOrdersEntity w WHERE w.productManager.id = ?1")
    List<WorkOrdersEntity> findByProductManagerId(Long id);
    // find by work order id
    @Modifying
    @Transactional
    @Query("DELETE FROM WorkOrdersEntity w WHERE w.id = ?1")
    void deleteByWOId(Long id);
    // find by in progress
    @Query("SELECT w FROM WorkOrdersEntity w WHERE w.dateStart = CURRENT_DATE")
    List<WorkOrdersEntity> findAllWorkOrdersStartingToday();
}
