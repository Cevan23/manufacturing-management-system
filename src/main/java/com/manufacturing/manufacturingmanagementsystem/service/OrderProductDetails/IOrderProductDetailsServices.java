package com.manufacturing.manufacturingmanagementsystem.service.OrderProductDetails;

import com.manufacturing.manufacturingmanagementsystem.models.OrderProductDetailsEntity;

import java.util.List;
import java.util.Map;

public interface IOrderProductDetailsServices {
    // Author: Nguyen Cao Nhan
    // This method is used to delete order product detail
    void deleteOrderProductDetail(Long pid, Long oid);
    // Author: Nguyen Cao Nhan
    // This method is used to update order product detail
    Map<String, Object> updateOrderProductDetail(Long oid, Long pid, Integer quantity);
    // Author: Nguyen Cao Nhan
    // This method is used to find order product detail by pid and oid
    OrderProductDetailsEntity findOrderProductDetailByPid_OrderID(Long pid, Long oid);
    // Author: Nguyen Cao Nhan
    // This method is used to insert order product detail
    List<Map<String, Object>> insertOrderProductDetail(Long oid, List<Long> pids, List<Integer> quantities);
    // Author: Nguyen Cao Nhan
    // This method is used to find order product detail by order id
    public List<Map<String, Object>> findOrderProductDetailById(Long id);
}

