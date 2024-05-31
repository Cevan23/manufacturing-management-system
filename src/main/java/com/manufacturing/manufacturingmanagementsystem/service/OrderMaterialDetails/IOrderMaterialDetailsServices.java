package com.manufacturing.manufacturingmanagementsystem.service.OrderMaterialDetails;

import com.manufacturing.manufacturingmanagementsystem.models.OrderMaterialDetailsEntity;

import java.util.List;
import java.util.Map;

public interface IOrderMaterialDetailsServices {
    // Author: Nguyen Cao Nhan
    // This method is used to delete order material detail
    void deleteOrderMaterialDetail(Long mid, Long oid);
    // Author: Nguyen Cao Nhan
    // This method is used to update order material detail
    Map<String, Object> updateOrderMaterialDetail(Long oid, Long mid, Integer quantity);
    // Author: Nguyen Cao Nhan
    // This method is used to find order material detail by mid and oid
    OrderMaterialDetailsEntity findOrderMaterialDetailByMid_OrderID(Long mid, Long oid);
    // Author: Nguyen Cao Nhan
    // This method is used to insert order material detail
    List<Map<String, Object>> insertOrderMaterialDetail(Long oid, List<Long> mids, List<Integer> quantities);
    // Author: Nguyen Cao Nhan
    // This method is used to find order material detail by order id
    public List<Map<String, Object>> findOrderMaterialDetailById(Long id);
}

