package com.manufacturing.manufacturingmanagementsystem.service.SaleForecastDetails;

import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.SaleForecastDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.ProductsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class SaleForecastDetailsServices implements ISaleForecastDetailsServices {

    private final SaleForecastDetailsRepository saleForecastDetailsRepository;
    private final SaleForecastsRepository saleForecastsRepository;
    private final ProductsRepository productsRepository;

    @Override
    public List<Map<String, Object>> insertSaleForecastDetail(Long sale_id, List<Long> pids, List<Integer> quantities) {
        try {
            SaleForecastsEntity saleForecast = saleForecastsRepository.findById(sale_id)
                    .orElseThrow(() -> new RuntimeException("Sale forecast not found with id: " + sale_id));
            List<Map<String, Object>> saleForecastDetailsMap = new ArrayList<>();
            List<SaleForecastDetailsEntity> saleForecastDetailsEntities;
            saleForecastDetailsEntities = new ArrayList<>();
            for (int i = 0; i < pids.size(); i++) {
                Map<String, Object> saleForecastDetailsEntry = new HashMap<>();
                Long productId = pids.get(i);
                Integer quantity = quantities.get(i);

                Optional<ProductsEntity> product = productsRepository.findById(productId);
                if (!product.isPresent()) {
                    throw new RuntimeException("Product not found with id: " + productId);
                }
                SaleForecastDetailsEntity saleForecastDetailsEntity = new SaleForecastDetailsEntity();
                SaleForecastDetailEntityId composedId = new SaleForecastDetailEntityId(productId, sale_id);
                saleForecastDetailsEntity.setId(composedId);
                saleForecastDetailsEntity.setSaleForecast(saleForecast);
                saleForecastDetailsEntity.setProduct(product.get());
                saleForecastDetailsEntity.setQuantity(quantity);
                saleForecastDetailsEntity.setTotalPrice(((float) (quantity * product.get().getPrice())));
                saleForecastDetailsEntity.setTotalSalePrice(((float) (quantity * product.get().getSellPrice())));

                saleForecastDetailsEntities.add(saleForecastDetailsEntity);

                saleForecastDetailsEntry.put("date_start", saleForecastDetailsEntity.getSaleForecast().getDateStart());
                saleForecastDetailsEntry.put("date_end", saleForecastDetailsEntity.getSaleForecast().getDateEnd());
                saleForecastDetailsEntry.put("name", saleForecastDetailsEntity.getProduct().getName());
                saleForecastDetailsEntry.put("quantity", saleForecastDetailsEntity.getQuantity());
                saleForecastDetailsEntry.put("totalPrice", saleForecastDetailsEntity.getTotalPrice());
                saleForecastDetailsEntry.put("totalSalePrice", saleForecastDetailsEntity.getTotalSalePrice());
                saleForecastDetailsMap.add(saleForecastDetailsEntry);

            }
            saleForecastDetailsRepository.saveAll(saleForecastDetailsEntities);
            return saleForecastDetailsMap;

        } catch (Exception e) {
            throw new RuntimeException("Failed to insert sale forecast detail: " + e.getMessage());
        }
    }

    @Override
    public SaleForecastDetailsEntity findSaleForecastDetailByPid_SaleID(Long pid, Long sale_id) {
        try {
            SaleForecastDetailEntityId compositeId = new SaleForecastDetailEntityId(pid, sale_id);
            Optional<SaleForecastDetailsEntity> saleForecastDetailsEntity = saleForecastDetailsRepository.findById(compositeId);
            if (saleForecastDetailsEntity.isPresent()) {
                return saleForecastDetailsEntity.get();
            } else {
                throw new RuntimeException("Sale forecast detail not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to find sale forecast detail: " + e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> findSaleForecastDetailById(Long id) {
        try {
            List<Map<String, Object>> saleForecastDetailsList = new ArrayList<>();
            List<SaleForecastDetailsEntity> saleForecastDetailsEntityList = saleForecastDetailsRepository.findListById(id);
            if (!saleForecastDetailsEntityList.isEmpty()) {
                for (SaleForecastDetailsEntity saleForecastDetailsEntity : saleForecastDetailsEntityList) {
                    Map<String, Object> saleForecastDetailsMap = new HashMap<>();
                    saleForecastDetailsMap.put("product_id", saleForecastDetailsEntity.getProduct().getId());
                    saleForecastDetailsMap.put("name", saleForecastDetailsEntity.getProduct().getName());
                    saleForecastDetailsMap.put("quantity", saleForecastDetailsEntity.getQuantity());
                    saleForecastDetailsMap.put("totalPrice", saleForecastDetailsEntity.getTotalPrice());
                    saleForecastDetailsMap.put("totalSalePrice", saleForecastDetailsEntity.getTotalSalePrice());
                    saleForecastDetailsList.add(saleForecastDetailsMap);
                }
            }
            return saleForecastDetailsList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to find list sale forecast detail: " + e.getMessage());
        }
    }


    @Override
    public Map<String, Object> updateSaleForecastDetail(Long sale_id, Long pid, Integer quantity,float totalPrice,float totalSaleprice) {
        try {
            Map<String, Object> saleForecastDetailsMap = new HashMap<>();
            Optional<ProductsEntity> product = productsRepository.findById(pid);
            if (product.isEmpty()) {
                throw new RuntimeException("Product not found with id: " + pid);
            }
            SaleForecastDetailsEntity saleForecastDetailsEntity = findSaleForecastDetailByPid_SaleID(pid,sale_id);
            if(quantity!=null){
                saleForecastDetailsEntity.setQuantity(quantity);
            }
            if (!Float.toString(totalPrice).isEmpty()) {
                saleForecastDetailsEntity.setTotalPrice(totalPrice);
            }
            if (!Float.toString(totalSaleprice).isEmpty()) {
                saleForecastDetailsEntity.setTotalSalePrice(totalSaleprice);
            }
            saleForecastDetailsRepository.save(saleForecastDetailsEntity);
            saleForecastDetailsMap.put("product_id", saleForecastDetailsEntity.getProduct().getId());
            saleForecastDetailsMap.put("name", saleForecastDetailsEntity.getProduct().getName());
            saleForecastDetailsMap.put("quantity", saleForecastDetailsEntity.getQuantity());
            saleForecastDetailsMap.put("totalPrice", saleForecastDetailsEntity.getTotalPrice());
            saleForecastDetailsMap.put("totalSalePrice", saleForecastDetailsEntity.getTotalSalePrice());
            return saleForecastDetailsMap;

        } catch (
                Exception e) {
            throw new RuntimeException("Failed to update sale forecast detail: " + e.getMessage());
        }
    }

    @Override
    public void deleteSaleForecastDetail(Long pid, Long sale_id) {
        try {
            SaleForecastDetailEntityId compositeId = new SaleForecastDetailEntityId(pid, sale_id);
            saleForecastDetailsRepository.deleteById(compositeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete sale forecast detail: " + e.getMessage());
        }
    }

    @Override
    public List<Object[]> findQuantityAndSaleForecastIdByProductIdAndMonthYear(Long productId, Date startDate, Date endDate) {
        try {
            return saleForecastDetailsRepository.findQuantityAndSaleForecastIdByProductIdAndMonthYear(productId, startDate, endDate);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find quantity and sale forecast id by product id and month year: " + e.getMessage());
        }
    }
    @Override
    public Map<String, Object> findSumReportSaleForecastById(Long id) {
        try {
            Map<String, Object> map = new HashMap<>();
            List<SaleForecastDetailsEntity> saleForecastDetailsEntityList = saleForecastDetailsRepository.findListById(id);
            Float totalPrice= 0f;
            Integer totalQuantity=  0;
            if (!saleForecastDetailsEntityList.isEmpty()) {
                for (SaleForecastDetailsEntity saleForecastDetailsEntity : saleForecastDetailsEntityList) {
                    totalPrice+=saleForecastDetailsEntity.getTotalSalePrice();
                    totalQuantity+=saleForecastDetailsEntity.getQuantity();
                }
            }
            map.put("total_price",totalPrice);
            map.put("total_quantity",totalQuantity);
            return map;
        } catch (Exception e) {
            throw new RuntimeException("Failed to find: " + e.getMessage());
        }
    }
}

