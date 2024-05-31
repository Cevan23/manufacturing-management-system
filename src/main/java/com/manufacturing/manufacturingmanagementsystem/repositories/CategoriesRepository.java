package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Author: Pham Van Cao
// this class is used to handle the CategoriesRepository response
@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {
    // Add custom query methods if needed
}
