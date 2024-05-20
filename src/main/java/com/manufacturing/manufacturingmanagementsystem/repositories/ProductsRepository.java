package com.manufacturing.manufacturingmanagementsystem.repositories;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    ProductsEntity findFirstByName(String name);

    @Query("SELECT p FROM ProductsEntity p WHERE p.bom.id = :bomId")
    List<ProductsEntity> findByBomId(@Param("bomId") Long bomId);
}
