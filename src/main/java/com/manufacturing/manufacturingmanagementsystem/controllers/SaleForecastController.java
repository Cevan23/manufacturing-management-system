package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ResponseObject;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import com.manufacturing.manufacturingmanagementsystem.service.SaleForecasts.SaleForecastsServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sale_forecast")
@RequiredArgsConstructor
public class SaleForecastController {
    private final SaleForecastsServices saleForecastsServices;
    @PostMapping("/create")
    public ResponseEntity<?> insertSaleForecast(@Valid @RequestParam("ac_id") Long ac_id) {
        try {
            SaleForecastsEntity saleForecast = saleForecastsServices.insertSaleForecast(ac_id);

            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .data(saleForecast)
                            .message("Add sale forecast successfully")
                            .status(HttpStatus.OK)
                            .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSaleForecast(@PathVariable Long id) {

        try {
            saleForecastsServices.deleteSaleForecast(id);
            return ResponseEntity.ok().body("Successfully delete sale forecast");
        }catch (Exception e) {
            // Xử lý lỗi và trả về phản hồi lỗi (status code 500)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
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
}
