package com.manufacturing.manufacturingmanagementsystem.service.SaleForecasts;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class SaleForecastsServices implements ISaleForecastsServices {

    private final SaleForecastsRepository saleForecastsRepository;
    private final UsersRepository usersRepository;

    @Override
    public SaleForecastsEntity insertSaleForecast(Long id) {
        try {
            SaleForecastsEntity saleForecastsEntity = new SaleForecastsEntity();
            saleForecastsEntity.setDateStart(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            if (usersRepository.findById(id).isPresent()){
                saleForecastsEntity.setAccountant(usersRepository.findById(id).get());
                if (!saleForecastsEntity.getAccountant().getRole().getRoleName().equals("ACCOUNTANT")){
                    throw new RuntimeException("Can not find accountant");
                }
            }else {
                throw new RuntimeException("Can not find accountant");
            }
            saleForecastsRepository.save(saleForecastsEntity);
            return saleForecastsEntity;
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert sale forecast: " + e.getMessage());
        }
    }

    @Override
    public void deleteSaleForecast(Long id) {
        try {
            saleForecastsRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete sale forecast: " + e.getMessage());
        }
    }

    @Override
    public SaleForecastsEntity findSaleForecastById(Long id) {
        try {
            Optional<SaleForecastsEntity> saleForecastsEntity = saleForecastsRepository.findById(id);
            if (saleForecastsEntity.isPresent()) {
                return saleForecastsEntity.get();
            } else {
                throw new RuntimeException("Sale forecast with id " + id + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to find sale forecast: " + e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> getAllSaleForecast() {
        try {
            List<Map<String, Object>> saleForecastMap = new ArrayList<>();
            List<SaleForecastsEntity> saleForecastsEntityList = saleForecastsRepository.findAll();

            for (SaleForecastsEntity saleForecastsEntity : saleForecastsEntityList) {
                Map<String, Object> saleForecastEntry = new HashMap<>();
                saleForecastEntry.put("id", saleForecastsEntity.getId());
                saleForecastEntry.put("dateStart", saleForecastsEntity.getDateStart());
                saleForecastEntry.put("dateEnd", saleForecastsEntity.getDateEnd());
                saleForecastMap.add(saleForecastEntry);
            }
            return saleForecastMap;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all sale forecast: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateSaleForecast(Long id,Date dateStart,Date dateEnd) {
        try {
            Map<String, Object> saleForecastMap = new HashMap<>();
            Optional<SaleForecastsEntity> optionalSaleForecastsEntity = saleForecastsRepository.findById(id);
            if(optionalSaleForecastsEntity.isPresent()){
                if(!dateStart.toString().isEmpty()){
                    optionalSaleForecastsEntity.get().setDateStart(dateStart);
                }
                if(!dateStart.toString().isEmpty()){
                    optionalSaleForecastsEntity.get().setDateEnd(dateEnd);
                }
                saleForecastsRepository.save(optionalSaleForecastsEntity.get());
                saleForecastMap.put("id", optionalSaleForecastsEntity.get().getId());
                saleForecastMap.put("dateStart", optionalSaleForecastsEntity.get().getDateStart());
                saleForecastMap.put("dateEnd", optionalSaleForecastsEntity.get().getDateEnd());
            }
            return saleForecastMap;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update sale forecast: " + e.getMessage());
        }
    }
}

