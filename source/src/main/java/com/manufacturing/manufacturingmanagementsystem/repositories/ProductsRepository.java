package com.manufacturing.manufacturingmanagementsystem.repositories;
import com.manufacturing.manufacturingmanagementsystem.model.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    ProductsEntity findFirstByName(String name);
}
