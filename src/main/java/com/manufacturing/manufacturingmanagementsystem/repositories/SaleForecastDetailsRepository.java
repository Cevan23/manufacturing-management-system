package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.SaleForecastDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SaleForecastDetailsRepository extends JpaRepository<SaleForecastDetailsEntity, SaleForecastDetailEntityId> {
    @Query("SELECT sd FROM SaleForecastDetailsEntity sd WHERE sd.id.saleForecastId = :sid")
    List<SaleForecastDetailsEntity> findListById(@Param("sid") long sid);

    @Query("SELECT sd.quantity, sd.saleForecast.id FROM SaleForecastDetailsEntity sd WHERE sd.product.id = :productId AND sd.saleForecast.dateStart >= :startDate AND sd.saleForecast.dateEnd <= :endDate")
    List<Object[]> findQuantityAndSaleForecastIdByProductIdAndMonthYear(@Param("productId") Long productId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
