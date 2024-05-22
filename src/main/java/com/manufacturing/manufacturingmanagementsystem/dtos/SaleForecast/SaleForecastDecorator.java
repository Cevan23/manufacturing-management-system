package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public abstract class SaleForecastDecorator implements ISaleForecast{
    private ISaleForecast iSaleForecast;
    @Override
    public List<SaleForecastsEntity> getAllSaleForecast(){
        return iSaleForecast.getAllSaleForecast();
    }
}
