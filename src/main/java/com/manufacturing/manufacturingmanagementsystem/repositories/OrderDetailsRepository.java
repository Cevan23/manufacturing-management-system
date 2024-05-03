package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.OrderProductDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderProductDetailsEntity, Long> {
//    @Query("SELECT p FROM ProductsEntity p " +
//            "CROSS JOIN OrderDetailsEntity o " +
//            "WHERE p.id = o.id.productId AND o.id.orderId = :oid")
//    List<ProductsEntity> findByOrderId(@Param("oid") long oid);
//
//    @Query("SELECT od FROM OrderProductDetailsEntity od WHERE od.id.orderId = :oid AND od.id.productId = :pid")
//    OrderProductDetailsEntity findByOrderId_ProductId(@Param("oid") long oid, @Param("pid") long pid);
}
