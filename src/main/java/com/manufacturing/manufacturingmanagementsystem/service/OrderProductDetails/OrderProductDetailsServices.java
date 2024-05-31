package com.manufacturing.manufacturingmanagementsystem.service.OrderProductDetails;

import com.manufacturing.manufacturingmanagementsystem.models.*;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderProductDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.SaleForecastDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.OrderProductDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.OrdersRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

// Author: Nguyen Cao Nhan
// This class is used to implement the IOrderProductDetailsServices interface
@Service
@AllArgsConstructor
public class OrderProductDetailsServices implements IOrderProductDetailsServices {

    private final OrderProductDetailsRepository orderProductDetailsRepository;
    private final ProductsRepository productsRepository;
    private final OrdersRepository ordersRepository;

    // Author: Nguyen Cao Nhan
    // This method is used to insert order product detail
    @Override
    public List<Map<String, Object>> insertOrderProductDetail(Long oid, List<Long> pids, List<Integer> quantities) {
        try {
            OrdersEntity ordersEntity = ordersRepository.findById(oid)
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + oid));
            List<Map<String, Object>> orderProductDetailsMap = new ArrayList<>();
            List<OrderProductDetailsEntity> orderProductDetailsEntities;
            orderProductDetailsEntities = new ArrayList<>();
            for (int i = 0; i < pids.size(); i++) {
                Map<String, Object> orderProductDetailsEntry = new HashMap<>();
                Long productId = pids.get(i);
                Integer quantity = quantities.get(i);

                Optional<ProductsEntity> product = productsRepository.findById(productId);
                if (!product.isPresent()) {
                    throw new RuntimeException("Product not found with id: " + productId);
                }
                OrderProductDetailsEntity orderProductDetailsEntity = new OrderProductDetailsEntity();
                OrderProductDetailEntityId composedId = new OrderProductDetailEntityId(productId, oid);
                orderProductDetailsEntity.setId(composedId);
                orderProductDetailsEntity.setOrder(ordersEntity);
                orderProductDetailsEntity.setProduct(product.get());
                orderProductDetailsEntity.setQuantity(quantity);
                orderProductDetailsEntity.setTotalUnitPrice(((float) (quantity * product.get().getSellPrice())));

                orderProductDetailsEntities.add(orderProductDetailsEntity);
                ordersEntity.setTotalPrice(ordersEntity.getTotalPrice()+orderProductDetailsEntity.getTotalUnitPrice());
                ordersRepository.save(ordersEntity);

                orderProductDetailsEntry.put("name", orderProductDetailsEntity.getProduct().getName());
                orderProductDetailsEntry.put("quantity", orderProductDetailsEntity.getQuantity());
                orderProductDetailsEntry.put("totalUnitPrice", orderProductDetailsEntity.getTotalUnitPrice());
                orderProductDetailsMap.add(orderProductDetailsEntry);

            }
            orderProductDetailsRepository.saveAll(orderProductDetailsEntities);
            return orderProductDetailsMap;

        } catch (Exception e) {
            throw new RuntimeException("Failed to insert product order detail: " + e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // This method is used to find order product detail by product id and order id
    @Override
    public OrderProductDetailsEntity findOrderProductDetailByPid_OrderID(Long pid, Long oid) {
        try {
            OrderProductDetailEntityId compositeId = new OrderProductDetailEntityId(pid, oid);
            Optional<OrderProductDetailsEntity> orderProductDetailsEntity = orderProductDetailsRepository.findById(compositeId);
            if (orderProductDetailsEntity.isPresent()) {
                return orderProductDetailsEntity.get();
            } else {
                throw new RuntimeException("Order product detail not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to find order product detail: " + e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // This method is used to find order product detail by order id
    @Override
    public List<Map<String, Object>> findOrderProductDetailById(Long id) {
        try {
            List<Map<String, Object>> orderProductDetailList = new ArrayList<>();
            List<OrderProductDetailsEntity> orderProductDetailsEntityList = orderProductDetailsRepository.findListById(id);
            if (!orderProductDetailsEntityList.isEmpty()) {
                for (OrderProductDetailsEntity orderProductDetailsEntity : orderProductDetailsEntityList) {
                    Map<String, Object> orderProductDetailsMap = new HashMap<>();
                    orderProductDetailsMap.put("product_id", orderProductDetailsEntity.getProduct().getId());
                    orderProductDetailsMap.put("name", orderProductDetailsEntity.getProduct().getName());
                    orderProductDetailsMap.put("quantity", orderProductDetailsEntity.getQuantity());
                    orderProductDetailsMap.put("totalUnitPrice", orderProductDetailsEntity.getTotalUnitPrice());;
                    orderProductDetailList.add(orderProductDetailsMap);
                }
            }
            return orderProductDetailList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to find list order product detail: " + e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // This method is used to update order product detail
    @Override
    public Map<String, Object> updateOrderProductDetail(Long oid, Long pid, Integer quantity) {
        try {
            Map<String, Object> orderProductMap = new HashMap<>();
            Optional<ProductsEntity> product = productsRepository.findById(pid);
            if (product.isEmpty()) {
                throw new RuntimeException("Product not found with id: " + pid);
            }
            OrderProductDetailsEntity orderProductDetailsEntity = findOrderProductDetailByPid_OrderID(pid,oid);
            OrdersEntity ordersEntity = ordersRepository.findById(oid)
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + oid));
            if(quantity!=null){
                ordersEntity.setTotalPrice(ordersEntity.getTotalPrice()-(float) (orderProductDetailsEntity.getQuantity()*product.get().getSellPrice()));
                orderProductDetailsEntity.setQuantity(quantity);
                orderProductDetailsEntity.setTotalUnitPrice((float) (quantity*product.get().getSellPrice()));
            }

            orderProductDetailsRepository.save(orderProductDetailsEntity);

            ordersEntity.setTotalPrice(ordersEntity.getTotalPrice()+orderProductDetailsEntity.getTotalUnitPrice());
            ordersRepository.save(ordersEntity);

            orderProductMap.put("product_id", orderProductDetailsEntity.getProduct().getId());
            orderProductMap.put("name", orderProductDetailsEntity.getProduct().getName());
            orderProductMap.put("quantity", orderProductDetailsEntity.getQuantity());
            orderProductMap.put("totalUnitPrice", orderProductDetailsEntity.getTotalUnitPrice());
            return orderProductMap;
        } catch (
                Exception e) {
            throw new RuntimeException("Failed to update product order detail: " + e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // This method is used to delete order product detail
    @Override
    public void deleteOrderProductDetail(Long pid, Long oid) {
        try {
            OrderProductDetailEntityId compositeId = new OrderProductDetailEntityId(pid, oid);
            Optional<OrderProductDetailsEntity> orderProductDetailsEntity = orderProductDetailsRepository.findById(compositeId);

            OrdersEntity ordersEntity = ordersRepository.findById(oid)
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + oid));
            ordersEntity.setTotalPrice(ordersEntity.getTotalPrice()-orderProductDetailsEntity.get().getTotalUnitPrice());
            if(ordersEntity.getTotalPrice()<0){
                ordersEntity.setTotalPrice((float) 0);
            }
            ordersRepository.save(ordersEntity);
            orderProductDetailsRepository.deleteById(compositeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete product order detail: " + e.getMessage());
        }
    }
}
