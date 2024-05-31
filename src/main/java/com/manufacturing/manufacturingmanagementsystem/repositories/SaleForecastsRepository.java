package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Author: Nguyen Cao Nhan
// This interface is a repository for the SaleForecastsEntity
@Repository
public interface SaleForecastsRepository extends JpaRepository<SaleForecastsEntity, Long> {
}
