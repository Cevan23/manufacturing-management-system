package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// Author: Pham Van Cao
// this class is used to handle the MaterialsRepository response
@Repository
public interface MaterialsRepository extends JpaRepository<MaterialsEntity, Long> {

    @Query("SELECT m FROM MaterialsEntity m WHERE m.name LIKE %:name%")
    Optional<List<MaterialsEntity>> findByNameContaining(String name);

    @NonNull
    Optional<MaterialsEntity> findByName(String name);

    @NonNull
    Optional<MaterialsEntity> findById(@NonNull Long id);
}
