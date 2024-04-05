package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.model.SaleForecastsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleForecastsRepository extends JpaRepository<SaleForecastsEntity, Long> {
    // Add custom query methods if needed
}
