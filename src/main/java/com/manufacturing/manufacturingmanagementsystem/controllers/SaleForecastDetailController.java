package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecastDetail.SaleForecastDetailInsertRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecastDetail.SaleForecastDetailUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ResponseObject;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.SaleForecastDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.service.SaleForecastDetails.SaleForecastDetailsServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sale_forecast_detail")
@RequiredArgsConstructor
public class SaleForecastDetailController {
    private final SaleForecastDetailsServices saleForecastDetailsServices;
    @PostMapping("/create")
    public ResponseEntity<?> insertSaleForecastDetail(@Valid @RequestBody SaleForecastDetailInsertRequest saleForecastDetailInsertRequest) {
        try {

            List<Map<String, Object>> list_sale_forecast_detail = saleForecastDetailsServices.insertSaleForecastDetail(saleForecastDetailInsertRequest.getSid(),saleForecastDetailInsertRequest.getPids(),saleForecastDetailInsertRequest.getQuantities());
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Add successfully")
                    .result(list_sale_forecast_detail)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("")
    public ResponseEntity<?> updateSaleForecastDetail(@Valid @RequestBody SaleForecastDetailUpdateRequest saleForecastDetailUpdateRequest) {
        try {

            Map<String, Object> sale_forecast_detail = saleForecastDetailsServices.updateSaleForecastDetail(saleForecastDetailUpdateRequest.getSid(),saleForecastDetailUpdateRequest.getPid(),saleForecastDetailUpdateRequest.getQuantity(),saleForecastDetailUpdateRequest.getTotalPrice(),saleForecastDetailUpdateRequest.getTotalSalePrice());
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Update successfully")
                    .result(sale_forecast_detail)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleForecastDetailById(@PathVariable Long id) {
        try {
            List<Map<String, Object>> sale_forecast_detail_list = saleForecastDetailsServices.findSaleForecastDetailById(id);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Get successfully")
                    .result(sale_forecast_detail_list)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{sid}")
    public ResponseEntity<?> DeleteSaleForecastDetail(@PathVariable Long sid,
                                                      @Valid @RequestParam("pid") Long pid) {
        try {
            saleForecastDetailsServices.deleteSaleForecastDetail(pid,sid);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Successfully delete sale forecast detail")
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
