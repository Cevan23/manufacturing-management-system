package com.manufacturing.manufacturingmanagementsystem.service.OrderDetails;

import com.manufacturing.manufacturingmanagementsystem.models.OrderProductDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.OrderDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderDetailsServices implements IOrderDetailsServices {

    // private final OrderDetailsRepository orderDetailsRepository;
    //
    // @Override
    // public List<Map<String, Object>> findProductByOrderId(Long oid) {
    // try {
    // List<Map<String, Object>> detailMap = new ArrayList<>();
    // List<ProductsEntity> productsEntityList =
    // orderDetailsRepository.findByOrderId(oid);
    // for (ProductsEntity product : productsEntityList) {
    // Map<String, Object> detailEntry = new HashMap<>();
    // OrderProductDetailsEntity orderProductDetailsEntity =
    // orderDetailsRepository.findByOrderId_ProductId(oid,product.getId());
    // detailEntry.put("name",product.getName());
    // detailEntry.put("unit",product.getUnit());
    // detailEntry.put("price",product.getPrice());
    // detailEntry.put("sell_price",product.getSellPrice());
    // detailEntry.put("volume",product.getVolume());
    // detailEntry.put("kind",product.getCategory().getCategoryName());
    // detailEntry.put("quantity", orderProductDetailsEntity.getQuantity());
    // detailEntry.put("total_price",
    // orderProductDetailsEntity.getTotalUnitPrice());
    // detailMap.add(detailEntry);
    // }
    // return detailMap;
    // } catch (Exception e) {
    // throw new RuntimeException("Failed to find products by pid & cid: " +
    // e.getMessage());
    // }
    // }
}
