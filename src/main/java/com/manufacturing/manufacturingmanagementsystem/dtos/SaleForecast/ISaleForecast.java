package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;

import java.util.List;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecast
public interface ISaleForecast {
    public List<SaleForecastsEntity> getAllSaleForecast(int month,int year);
}
