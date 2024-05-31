package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecastImpl
@AllArgsConstructor
@NoArgsConstructor
public class SaleForecastImpl implements ISaleForecast {
    private List<SaleForecastsEntity> saleForecastDetailsDTOList;
    private SaleForecastsRepository saleForecastsRepository;

    public SaleForecastImpl(SaleForecastsRepository saleForecastsRepository) {
        this.saleForecastsRepository = saleForecastsRepository;
    }

    @Override
    public List<SaleForecastsEntity> getAllSaleForecast(int month,int year){
        return saleForecastsRepository.findAll();
    }
}
