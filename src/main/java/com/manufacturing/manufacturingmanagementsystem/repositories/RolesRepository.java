package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    @Query("SELECT r.roleName FROM RolesEntity r WHERE r.id = :id")
    String findRoleNameById(@Param("id") Long id);

    @Query("SELECT r.id FROM RolesEntity r WHERE r.roleName = :roleName")
    Long findIdByRoleName(@Param("roleName") String roleName);

    @Query("SELECT r FROM RolesEntity r WHERE r.roleName = :roleName")
    RolesEntity findRoleByRoleName(@Param("roleName") String roleName);

    @Query("SELECT r FROM RolesEntity r WHERE r.id = :id")
    RolesEntity findRoleById(@Param("id") Long id);
}
