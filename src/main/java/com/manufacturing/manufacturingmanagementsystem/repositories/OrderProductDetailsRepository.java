package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.OrderProductDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderProductDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
// Author: Nguyen Cao Nhan
// This interface is used to interact with the database to retrieve, insert, update, delete OrderProductDetailsEntity objects
@Repository
public interface OrderProductDetailsRepository extends JpaRepository<OrderProductDetailsEntity, OrderProductDetailEntityId> {
    // Author: Nguyen Cao Nhan
    // This method is used to find a list of OrderProductDetailsEntity by order id
    @Query("SELECT opd FROM OrderProductDetailsEntity opd WHERE opd.id.orderId = :oid")
    List<OrderProductDetailsEntity> findListById(@Param("oid") long oid);

}
