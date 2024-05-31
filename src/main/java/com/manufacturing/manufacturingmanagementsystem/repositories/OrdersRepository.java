package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Author: Nguyen Cao Nhan
@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {

}
