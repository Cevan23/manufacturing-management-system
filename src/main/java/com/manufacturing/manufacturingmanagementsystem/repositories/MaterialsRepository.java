package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialsRepository extends JpaRepository<MaterialsEntity, Long> {
    @NonNull
    Optional<MaterialsEntity> findByName(String name);
    @NonNull
    Optional<MaterialsEntity> findById(@NonNull Long id);
}
