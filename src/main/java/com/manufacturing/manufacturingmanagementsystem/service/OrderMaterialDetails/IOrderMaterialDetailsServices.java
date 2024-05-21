package com.manufacturing.manufacturingmanagementsystem.service.OrderMaterialDetails;

import com.manufacturing.manufacturingmanagementsystem.models.OrderMaterialDetailsEntity;

import java.util.List;
import java.util.Map;

public interface IOrderMaterialDetailsServices {
    void deleteOrderMaterialDetail(Long mid, Long oid);
    Map<String, Object> updateOrderMaterialDetail(Long oid, Long mid, Integer quantity);
    OrderMaterialDetailsEntity findOrderMaterialDetailByMid_OrderID(Long mid, Long oid);
    List<Map<String, Object>> insertOrderMaterialDetail(Long oid, List<Long> mids, List<Integer> quantities);
    public List<Map<String, Object>> findOrderMaterialDetailById(Long id);
}

