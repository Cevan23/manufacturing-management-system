package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.CustomersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
// Author: Pham Hien Nhan
// this class is used to handle the CustomersRepository response
@Repository
public interface CustomersRepository extends JpaRepository<CustomersEntity, Long> {
    @Query("SELECT c FROM CustomersEntity c WHERE c.contact = :contact")
    CustomersEntity findCustomerByContact(@Param("contact") String contact);
}
