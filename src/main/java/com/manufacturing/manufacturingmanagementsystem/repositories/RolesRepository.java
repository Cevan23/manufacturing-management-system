package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the RolesRepository response
@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    // find role name by id
    @Query("SELECT r.roleName FROM RolesEntity r WHERE r.id = :id")
    String findRoleNameById(@Param("id") Long id);
    // find id by role name
    @Query("SELECT r.id FROM RolesEntity r WHERE r.roleName = :roleName")
    Long findIdByRoleName(@Param("roleName") String roleName);
    // find role by role name
    @Query("SELECT r FROM RolesEntity r WHERE r.roleName = :roleName")
    RolesEntity findRoleByRoleName(@Param("roleName") String roleName);
    // find role by id
    @Query("SELECT r FROM RolesEntity r WHERE r.id = :id")
    RolesEntity findRoleById(@Param("id") Long id);
    // find permissions by role id
    @Query("SELECT r.permissions FROM RolesEntity r WHERE r.id = :id")
    List<PermissionsEntity> findPermissionsByRoleId(@Param("id") Long id);

}
