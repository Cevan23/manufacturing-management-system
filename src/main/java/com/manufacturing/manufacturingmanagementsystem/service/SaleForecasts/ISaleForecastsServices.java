package com.manufacturing.manufacturingmanagementsystem.service.SaleForecasts;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ISaleForecastsServices {
    // Author: Nguyen Cao Nhan
    // Service insert SaleForecast
    SaleForecastsEntity insertSaleForecast(Long id);
    // Author: Nguyen Cao Nhan
    // Service delete SaleForecast
    public void deleteSaleForecast(Long id);
    // Author: Nguyen Cao Nhan
    // Service find SaleForecast by id
    SaleForecastsEntity findSaleForecastById(Long id);
    // Author: Nguyen Cao Nhan
    // Service get all SaleForecast
    public List<Map<String, Object>> getAllSaleForecast();
    // Author: Nguyen Cao Nhan
    // Service update SaleForecast
    public Map<String, Object> updateSaleForecast(Long id, Date dateStart, Date dateEnd);


}

