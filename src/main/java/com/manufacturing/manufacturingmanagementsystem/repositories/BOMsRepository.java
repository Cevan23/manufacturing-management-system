package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// Author: Pham Van Cao
// this class is used to handle the BOMsRepository response
@Repository
public interface BOMsRepository extends JpaRepository<BOMsEntity, Long> {
    // find by name
    @Query("SELECT b FROM BOMsEntity b WHERE b.name = :name")
    Optional<BOMsEntity> findByName(@Param("name") String name);
    // find by status
    @Query("SELECT b FROM BOMsEntity b WHERE b.BOMstatus = :status")
    List<BOMsEntity> findByBOMstatus(@Param("status") String status);
    // find by name like
    @Query("SELECT b FROM BOMsEntity b WHERE b.name LIKE %:name%")
    List<BOMsEntity> findByNameLike(@Param("name") String name);
    // find by id
    @Query("SELECT b FROM BOMsEntity b WHERE b.id = :id")
    Optional<BOMsEntity> findById(@Param("id") Long id);

}
