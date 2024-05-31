package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast.ISaleForecast;
import com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast.SaleForecastImpl;
import com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast.SaleForecastMonth;
import com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast.SaleForecastYear;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastsRepository;
import com.manufacturing.manufacturingmanagementsystem.service.SaleForecastDetails.SaleForecastDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Author: Nguyen Cao Nhan
@RestController
@RequestMapping("/api/sale_forecasts/year")
@AllArgsConstructor
public class SaleForecastYearController {
    private final SaleForecastsRepository saleForecastsRepository;
    private final SaleForecastDetailsServices saleForecastDetailsServices;
    // Author: Nguyen Cao Nhan
    // Get All Sale Forecasts By Year Controller
    @GetMapping("/{year}")
    public ResponseEntity<?> getAllSaleForecastsByYear(@PathVariable int year) {
        try {
            Map<String, Object> map = new HashMap<>();
            ISaleForecast sale1 = new SaleForecastYear(new SaleForecastImpl(saleForecastsRepository));
            List<SaleForecastsEntity> list= sale1.getAllSaleForecast(0,year);
            Float totalSalePrice= 0f;
            Integer totalQuantity= 0;
            for (SaleForecastsEntity saleForecastsEntity : list) {
                Map<String, Object> sumReport = saleForecastDetailsServices.findSumReportSaleForecastById(saleForecastsEntity.getId());
                totalSalePrice += (float) sumReport.get("total_price");
                totalQuantity += (Integer) sumReport.get("total_quantity");
            }
            map.put("total_price",totalSalePrice);
            map.put("total_quantity",totalQuantity);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Get successfully")
                    .result(map)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
