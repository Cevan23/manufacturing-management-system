package com.manufacturing.manufacturingmanagementsystem.service.Orders;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Customer.CustomerResponse;
import com.manufacturing.manufacturingmanagementsystem.models.CustomersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.CustomersRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.OrdersRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Customers.CustomersServices;
import com.manufacturing.manufacturingmanagementsystem.service.OrderProductDetails.OrderProductDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class OrdersServices implements IOrdersServices {

    private final OrdersRepository ordersRepository;
    private final OrderProductDetailsServices orderDetailsServices;
    private final CustomersRepository customersRepository;
    private final CustomersServices customersServices;
    private final UsersRepository usersRepository;


    @Override
    public List<Map<String, Object>> getAllOrders() {
        try {
            List<Map<String, Object>> orderMap = new ArrayList<>();
            List<OrdersEntity> orders = ordersRepository.findAll();

            for (OrdersEntity order : orders) {
                Map<String, Object> orderEntry = new HashMap<>();
                orderEntry.put("id", order.getId());
                orderEntry.put("dateStart", order.getDateStart());
                orderEntry.put("dateEnd", order.getDateEnd());
                orderEntry.put("kindOrder", order.getKindOrder());
                orderEntry.put("totalPrice", order.getTotalPrice());
                orderEntry.put("orderStatus", order.getOrderStatus());
                orderEntry.put("customer", CustomerResponse.fromCustomer(order.getCustomer()));
                orderMap.add(orderEntry);
            }
            return orderMap;
        } catch (Exception e) {
            throw new RuntimeException("Failed to find orders by Id: " + e.getMessage());
        }
    }

    @Override
    public OrdersEntity insertOrder(OrderRequest orderRequest) {
        try {
            OrdersEntity ordersEntity = new OrdersEntity();
            CustomersEntity customersEntity = customersRepository.findCustomerByContact(orderRequest.getContact());
            if(customersEntity==null){
                customersServices.insertCustomerForOrder(orderRequest.getName(),orderRequest.getContact());
            }
            if (usersRepository.findById(orderRequest.getAccountants_id()).isPresent()){
                ordersEntity.setAccountant(usersRepository.findById(orderRequest.getAccountants_id()).get());
                if (!ordersEntity.getAccountant().getRole().getRoleName().equals("ACCOUNTANT")){
                    throw new RuntimeException("Can not find accountant");
                }
            }else {
                throw new RuntimeException("Can not find accountant");
            }
            ordersEntity.setKindOrder(orderRequest.getKindOrder());
            ordersEntity.setOrderStatus("PENDING");
            ordersEntity.setCustomer(customersEntity);
            ordersEntity.setDateStart(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            ordersEntity.setDateEnd(orderRequest.getDateEnd());
            ordersRepository.save(ordersEntity);
            return ordersEntity;
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert order: " + e.getMessage());
        }
    }

    @Override
    public OrdersEntity updateOrder(OrderUpdateRequest orderUpdateRequest) {
        try {
            Optional<OrdersEntity> ordersEntity = ordersRepository.findById(orderUpdateRequest.getId());
            if(!ordersEntity.isPresent()){
                throw new RuntimeException("Failed to find order: ");
            }
            ordersEntity.get().setOrderStatus(orderUpdateRequest.getOrderStatus());
            ordersEntity.get().setDateStart(orderUpdateRequest.getDateStart());
            ordersEntity.get().setDateEnd(orderUpdateRequest.getDateEnd());
            ordersRepository.save(ordersEntity.get());
            return ordersEntity.get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update order: " + e.getMessage());
        }

    }

    @Override
    public void deleteOrder(Long id) {
        try {
            ordersRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete order: " + e.getMessage());
        }
    }
}

