package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class SaleForecastImpl implements ISaleForecast {
    private List<SaleForecastsEntity> saleForecastDetailsDTOList;
    private SaleForecastsRepository saleForecastsRepository;
    @Override
    public List<SaleForecastsEntity> getAllSaleForecast(){
        return saleForecastsRepository.findAll();
    }
}
