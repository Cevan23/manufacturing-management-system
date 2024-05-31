package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecast.SaleForecastRequestUpdate;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import com.manufacturing.manufacturingmanagementsystem.service.SaleForecasts.SaleForecastsServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
// Author: Nguyen Cao Nhan
@RestController
@RequestMapping("/api/sale_forecast")
@RequiredArgsConstructor
public class SaleForecastController {
    private final SaleForecastsServices saleForecastsServices;
    // Author: Nguyen Cao Nhan
    // Insert Sale Forecast Controller
    @PostMapping("/create")
    public ResponseEntity<?> insertSaleForecast(@Valid @RequestParam("ac_id") Long ac_id) {
        try {
            SaleForecastsEntity saleForecast = saleForecastsServices.insertSaleForecast(ac_id);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Add sale forecast successfully")
                    .result(saleForecast)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // Delete Sale Forecast Controller
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSaleForecast(@PathVariable Long id) {
        try {
            saleForecastsServices.deleteSaleForecast(id);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Successfully delete sale forecast")
                    .build());
        }catch (Exception e) {
            // Xử lý lỗi và trả về phản hồi lỗi (status code 500)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // Get All Sale Forecast Controller
    @GetMapping("")
    public ResponseEntity<?> getSaleForecast() {
        try {
            List<Map<String, Object>> listSaleForecast = saleForecastsServices.getAllSaleForecast();
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Get all sale forecast successfully")
                    .result(listSaleForecast)
                    .build());
        }catch (Exception e) {
            // Xử lý lỗi và trả về phản hồi lỗi (status code 500)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // Update Sale Forecast Controller
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSaleForecast(@PathVariable Long id,
                                                @Valid @RequestBody SaleForecastRequestUpdate saleForecastRequestUpdate) {
        try {
            Map<String, Object> saleForecast = saleForecastsServices.updateSaleForecast(id,saleForecastRequestUpdate.getDateStart(),saleForecastRequestUpdate.getDateEnd());
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Update successfully")
                    .result(saleForecast)
                    .build());
        }catch (Exception e) {
            // Xử lý lỗi và trả về phản hồi lỗi (status code 500)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
