package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.email = :email")
    Optional<UsersEntity> findByEmail(String email);

    @Query("SELECT u FROM UsersEntity u WHERE u.email = :email")
    UsersEntity getInfoByEmail(String email);

    @Query("SELECT u FROM UsersEntity u WHERE u.id = :id")
    Optional<UsersEntity> findById(@Param("id") Long id);

    List<UsersEntity> findAll();

    @Query("SELECT u FROM UsersEntity u WHERE u.role.roleName = :roleName")
    UsersEntity findByRole(@Param("roleName") String roleName);

    @Query("SELECT u FROM UsersEntity u WHERE u.role.id is null")
    List<UsersEntity> findNullRoleId();

    @Query("SELECT u FROM UsersEntity u WHERE u.role.id is not null")
    List<UsersEntity> findNotNullRoleId();

}
