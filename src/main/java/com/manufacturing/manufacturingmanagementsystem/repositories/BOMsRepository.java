package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BOMsRepository extends JpaRepository<BOMsEntity, Long> {

    BOMsEntity findByName(String name);

    @Query("SELECT b FROM BOMsEntity b WHERE b.BOMstatus = ?1")
    List<BOMsEntity> findByBOMstatus(String status);

    @Query("SELECT b FROM BOMsEntity b WHERE b.name LIKE %?1%")
    List<BOMsEntity> findByNameLike(String name);
}
