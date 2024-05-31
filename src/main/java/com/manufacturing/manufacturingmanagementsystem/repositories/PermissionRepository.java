package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Author: Pham Van Cao
// this class is used to handle the PermissionRepository response
@Repository
public interface PermissionRepository extends JpaRepository<PermissionsEntity, String> {
    PermissionsEntity findByName(String name);

}