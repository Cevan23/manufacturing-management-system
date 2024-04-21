package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.OrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, OrderDetailEntityId> {
    // Add custom query methods if needed
}
