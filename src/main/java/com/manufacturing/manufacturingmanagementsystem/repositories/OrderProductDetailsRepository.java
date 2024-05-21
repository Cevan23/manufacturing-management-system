package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.OrderProductDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderProductDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDetailsRepository extends JpaRepository<OrderProductDetailsEntity, OrderProductDetailEntityId> {
//    @Query("SELECT p FROM ProductsEntity p " +
//            "CROSS JOIN OrderDetailsEntity o " +
//            "WHERE p.id = o.id.productId AND o.id.orderId = :oid")
//    List<ProductsEntity> findByOrderId(@Param("oid") long oid);

    @Query("SELECT opd FROM OrderProductDetailsEntity opd WHERE opd.id.orderId = :oid")
    List<OrderProductDetailsEntity> findListById(@Param("oid") long oid);

}
