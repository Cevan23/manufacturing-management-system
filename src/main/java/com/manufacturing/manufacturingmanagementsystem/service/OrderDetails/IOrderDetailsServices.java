package com.manufacturing.manufacturingmanagementsystem.service.OrderDetails;

import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;

import java.util.List;
import java.util.Map;

public interface IOrderDetailsServices {
    List<Map<String, Object>> findProductByOrderId(Long oid);
}

