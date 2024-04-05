package com.manufacturing.manufacturingmanagementsystem.service.SaleForecasts;

import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleForecastsServices implements ISaleForecastsServices {

    private final SaleForecastsRepository saleForecastsRepository;

    // Các phương thức service khác cần thiết
}

