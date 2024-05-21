package com.manufacturing.manufacturingmanagementsystem.service.OrderProductDetails;

import com.manufacturing.manufacturingmanagementsystem.models.OrderProductDetailsEntity;

import java.util.List;
import java.util.Map;

public interface IOrderProductDetailsServices {
    void deleteOrderProductDetail(Long pid, Long oid);
    Map<String, Object> updateOrderProductDetail(Long oid, Long pid, Integer quantity);
    OrderProductDetailsEntity findOrderProductDetailByPid_OrderID(Long pid, Long oid);
    List<Map<String, Object>> insertOrderProductDetail(Long oid, List<Long> pids, List<Integer> quantities);
    public List<Map<String, Object>> findOrderProductDetailById(Long id);
}

