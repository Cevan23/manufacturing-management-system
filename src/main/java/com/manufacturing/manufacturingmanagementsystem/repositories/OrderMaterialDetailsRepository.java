package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.OrderMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.OrderProductDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderMaterialDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderProductDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMaterialDetailsRepository extends JpaRepository<OrderMaterialDetailsEntity, OrderMaterialDetailEntityId> {
    @Query("SELECT omd FROM OrderMaterialDetailsEntity omd WHERE omd.id.orderId = :oid")
    List<OrderMaterialDetailsEntity> findListById(@Param("oid") long oid);

}
