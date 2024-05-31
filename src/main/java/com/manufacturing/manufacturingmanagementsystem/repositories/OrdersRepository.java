package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Author: Nguyen Cao Nhan
// This interface is a repository for the OrdersEntity
@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {

}
