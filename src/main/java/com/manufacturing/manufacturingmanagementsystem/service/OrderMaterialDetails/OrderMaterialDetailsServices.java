package com.manufacturing.manufacturingmanagementsystem.service.OrderMaterialDetails;

import com.manufacturing.manufacturingmanagementsystem.models.OrderMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderMaterialDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.OrderMaterialDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.OrdersRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.MaterialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderMaterialDetailsServices implements IOrderMaterialDetailsServices {

    private final OrderMaterialDetailsRepository orderMaterialDetailsRepository;
    private final MaterialsRepository MaterialsRepository;
    private final OrdersRepository ordersRepository;
    @Override
    public List<Map<String, Object>> insertOrderMaterialDetail(Long oid, List<Long> mids, List<Integer> quantities) {
        try {
            OrdersEntity ordersEntity = ordersRepository.findById(oid)
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + oid));
            List<Map<String, Object>> orderMaterialDetailsMap = new ArrayList<>();
            List<OrderMaterialDetailsEntity> orderMaterialDetailsEntities;
            orderMaterialDetailsEntities = new ArrayList<>();
            for (int i = 0; i < mids.size(); i++) {
                Map<String, Object> orderMaterialDetailsEntry = new HashMap<>();
                Long mid = mids.get(i);
                Integer quantity = quantities.get(i);

                Optional<MaterialsEntity> Material = MaterialsRepository.findById(mid);
                if (!Material.isPresent()) {
                    throw new RuntimeException("Material not found with id: " + mid);
                }
                OrderMaterialDetailsEntity orderMaterialDetailsEntity = new OrderMaterialDetailsEntity();
                OrderMaterialDetailEntityId composedId = new OrderMaterialDetailEntityId(mid, oid);
                orderMaterialDetailsEntity.setId(composedId);
                orderMaterialDetailsEntity.setOrder(ordersEntity);
                orderMaterialDetailsEntity.setMaterial(Material.get());
                orderMaterialDetailsEntity.setQuantity(quantity);
                orderMaterialDetailsEntity.setTotalUnitPrice(((float) (quantity * Material.get().getPrice())));

                orderMaterialDetailsEntities.add(orderMaterialDetailsEntity);

                orderMaterialDetailsEntry.put("name", orderMaterialDetailsEntity.getMaterial().getName());
                orderMaterialDetailsEntry.put("quantity", orderMaterialDetailsEntity.getQuantity());
                orderMaterialDetailsEntry.put("totalUnitPrice", orderMaterialDetailsEntity.getTotalUnitPrice());
                orderMaterialDetailsMap.add(orderMaterialDetailsEntry);

            }
            orderMaterialDetailsRepository.saveAll(orderMaterialDetailsEntities);
            return orderMaterialDetailsMap;

        } catch (Exception e) {
            throw new RuntimeException("Failed to insert material order detail: " + e.getMessage());
        }
    }

    @Override
    public OrderMaterialDetailsEntity findOrderMaterialDetailByMid_OrderID(Long mid, Long oid) {
        try {
            OrderMaterialDetailEntityId compositeId = new OrderMaterialDetailEntityId(mid, oid);
            Optional<OrderMaterialDetailsEntity> orderMaterialDetailsEntity = orderMaterialDetailsRepository.findById(compositeId);
            if (orderMaterialDetailsEntity.isPresent()) {
                return orderMaterialDetailsEntity.get();
            } else {
                throw new RuntimeException("Order material detail not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to find order material detail: " + e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> findOrderMaterialDetailById(Long id) {
        try {
            List<Map<String, Object>> orderMaterialDetailList = new ArrayList<>();
            List<OrderMaterialDetailsEntity> orderMaterialDetailsEntityList = orderMaterialDetailsRepository.findListById(id);
            if (!orderMaterialDetailsEntityList.isEmpty()) {
                for (OrderMaterialDetailsEntity orderMaterialDetailsEntity : orderMaterialDetailsEntityList) {
                    Map<String, Object> orderMaterialDetailsMap = new HashMap<>();
                    orderMaterialDetailsMap.put("material_id", orderMaterialDetailsEntity.getMaterial().getId());
                    orderMaterialDetailsMap.put("name", orderMaterialDetailsEntity.getMaterial().getName());
                    orderMaterialDetailsMap.put("quantity", orderMaterialDetailsEntity.getQuantity());
                    orderMaterialDetailsMap.put("totalUnitPrice", orderMaterialDetailsEntity.getTotalUnitPrice());;
                    orderMaterialDetailList.add(orderMaterialDetailsMap);
                }
            }
            return orderMaterialDetailList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to find list order material detail: " + e.getMessage());
        }
    }


    @Override
    public Map<String, Object> updateOrderMaterialDetail(Long oid, Long mid, Integer quantity) {
        try {
            Map<String, Object> orderMaterialMap = new HashMap<>();
            Optional<MaterialsEntity> Material = MaterialsRepository.findById(mid);
            if (Material.isEmpty()) {
                throw new RuntimeException("Material not found with id: " + mid);
            }
            OrderMaterialDetailsEntity orderMaterialDetailsEntity = findOrderMaterialDetailByMid_OrderID(mid,oid);
            if(quantity!=null){
                orderMaterialDetailsEntity.setQuantity(quantity);
                orderMaterialDetailsEntity.setTotalUnitPrice((float) (quantity*Material.get().getPrice()));
            }

            orderMaterialDetailsRepository.save(orderMaterialDetailsEntity);
            orderMaterialMap.put("material_id", orderMaterialDetailsEntity.getMaterial().getId());
            orderMaterialMap.put("name", orderMaterialDetailsEntity.getMaterial().getName());
            orderMaterialMap.put("quantity", orderMaterialDetailsEntity.getQuantity());
            orderMaterialMap.put("totalUnitPrice", orderMaterialDetailsEntity.getTotalUnitPrice());
            return orderMaterialMap;

        } catch (
                Exception e) {
            throw new RuntimeException("Failed to update material order detail: " + e.getMessage());
        }
    }

    @Override
    public void deleteOrderMaterialDetail(Long mid, Long oid) {
        try {
            OrderMaterialDetailEntityId compositeId = new OrderMaterialDetailEntityId(mid, oid);
            orderMaterialDetailsRepository.deleteById(compositeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete material order detail: " + e.getMessage());
        }
    }
}
