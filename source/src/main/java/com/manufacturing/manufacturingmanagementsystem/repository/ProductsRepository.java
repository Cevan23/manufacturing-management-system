package com.manufacturing.manufacturingmanagementsystem.repository;
import com.manufacturing.manufacturingmanagementsystem.model.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    ProductsEntity findFirstByName(String name);
}
