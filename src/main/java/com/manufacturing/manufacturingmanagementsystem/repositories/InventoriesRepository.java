package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Author: Pham Hien Nhan
// this class is used to handle the InventoriesRepository response
@Repository
public interface InventoriesRepository extends JpaRepository<InventoriesEntity, Long> {

}
