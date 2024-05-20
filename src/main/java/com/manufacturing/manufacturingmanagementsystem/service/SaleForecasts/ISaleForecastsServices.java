package com.manufacturing.manufacturingmanagementsystem.service.SaleForecasts;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ISaleForecastsServices {
    SaleForecastsEntity insertSaleForecast(Long id);
    public void deleteSaleForecast(Long id);
    SaleForecastsEntity findSaleForecastById(Long id);
    public List<Map<String, Object>> getAllSaleForecast();
    public Map<String, Object> updateSaleForecast(Long id, Date dateStart, Date dateEnd);
}

