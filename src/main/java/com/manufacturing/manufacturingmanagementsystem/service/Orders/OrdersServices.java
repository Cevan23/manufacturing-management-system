package com.manufacturing.manufacturingmanagementsystem.service.Orders;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Customer.CustomerResponse;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.OrdersRepository;
import com.manufacturing.manufacturingmanagementsystem.service.OrderDetails.OrderDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrdersServices implements IOrdersServices {

//    private final OrdersRepository ordersRepository;
//    private final OrderDetailsServices orderDetailsServices;
//
//
//    @Override
//    public List<Map<String, Object>> getAllOrders() {
//        try {
//            List<Map<String, Object>> orderMap = new ArrayList<>();
//            List<OrdersEntity> orders = ordersRepository.findAll();
//
//            for (OrdersEntity order : orders) {
//                Map<String, Object> orderEntry = new HashMap<>();
//                orderEntry.put("dateStart", order.getDateStart());
//                orderEntry.put("dateEnd", order.getDateEnd());
//                orderEntry.put("kindOrder", order.getKindOrder());
//                orderEntry.put("totalPrice", order.getTotalPrice());
//                orderEntry.put("orderStatus", order.getOrderStatus());
//                orderEntry.put("customer", CustomerResponse.fromCustomer(order.getCustomer()));
//                orderEntry.put("products", orderDetailsServices.findProductByOrderId(order.getId()));
//                orderMap.add(orderEntry);
//            }
//            return orderMap;
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to find orders by Id: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public OrdersEntity insertOrder(UsersDTO userForm) {
//        return null;
//    }
//
//    @Override
//    public OrdersEntity updateOrder(long id, UsersDTO userForm) {
//        return null;
//    }
//
//    @Override
//    public void deleteOrder(Long id) {
//
//    }
}

