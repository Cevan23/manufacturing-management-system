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

// Author: Nguyen Cao Nhan
// This interface is used to interact with the database to retrieve, insert, update, delete OrderMaterialDetailsEntity objects
@Repository
public interface OrderMaterialDetailsRepository extends JpaRepository<OrderMaterialDetailsEntity, OrderMaterialDetailEntityId> {
    // Author: Nguyen Cao Nhan
    // This method is used to find a list of OrderMaterialDetailsEntity by order id
    @Query("SELECT omd FROM OrderMaterialDetailsEntity omd WHERE omd.id.orderId = :oid")
    List<OrderMaterialDetailsEntity> findListById(@Param("oid") long oid);

}
