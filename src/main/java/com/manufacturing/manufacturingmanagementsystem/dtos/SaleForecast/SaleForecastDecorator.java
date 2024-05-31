package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecastDecorator
@AllArgsConstructor
@NoArgsConstructor
public abstract class SaleForecastDecorator implements ISaleForecast{
    private ISaleForecast iSaleForecast;
    @Override
    public List<SaleForecastsEntity> getAllSaleForecast(int month,int year){
        return iSaleForecast.getAllSaleForecast(month,year);
    }
}
