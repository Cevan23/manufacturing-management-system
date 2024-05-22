package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.SaleForecastDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleForecastDetailsRepository extends JpaRepository<SaleForecastDetailsEntity, SaleForecastDetailEntityId> {
    @Query("SELECT sd FROM SaleForecastDetailsEntity sd WHERE sd.id.saleForecastId = :sid")
    List<SaleForecastDetailsEntity> findListById(@Param("sid") long sid);
}
