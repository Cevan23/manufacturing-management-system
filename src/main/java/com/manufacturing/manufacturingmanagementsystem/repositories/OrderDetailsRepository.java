package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.OrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long> {
    @Query("SELECT p FROM ProductsEntity p " +
            "CROSS JOIN OrderDetailsEntity o " +
            "WHERE p.id = o.id.productId AND o.id.orderId = :oid")
    List<ProductsEntity> findByOrderId(@Param("oid") long oid);

    @Query("SELECT od FROM OrderDetailsEntity od WHERE od.orderId = :oid AND od.productId = :pid")
    OrderDetailsEntity findByOrderId_ProductId(@Param("oid") long oid,@Param("pid") long pid);
}
