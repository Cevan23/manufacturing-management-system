package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionsEntity, String> {
    PermissionsEntity findByName(String name);

}