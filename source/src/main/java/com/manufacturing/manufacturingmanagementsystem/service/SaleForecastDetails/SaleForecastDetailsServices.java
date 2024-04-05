package com.manufacturing.manufacturingmanagementsystem.service.SaleForecastDetails;

import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleForecastDetailsServices implements ISaleForecastDetailsServices {

    private final SaleForecastDetailsRepository saleForecastDetailsRepository;

    // Các phương thức service khác cần thiết
}

