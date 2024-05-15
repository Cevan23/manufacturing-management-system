package com.manufacturing.manufacturingmanagementsystem.service.MasterProductionSchedules;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSRequest;
import com.manufacturing.manufacturingmanagementsystem.models.MasterProductionSchedulesEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.MasterProductionSchedulesRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.ProductsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Users.UsersServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterProductionSchedulesServices implements IMasterProductionSchedulesServices {

    private final MasterProductionSchedulesRepository masterProductionSchedulesRepository;
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;

    @Override
    public void createMPS(MPSRequest mpsRequest) {
        var productManager = usersRepository.findById(mpsRequest.getProduct_manager_ID()).orElseThrow();
        var product = productsRepository.findById(mpsRequest.getProductId()).orElseThrow();

        MasterProductionSchedulesEntity mps = MasterProductionSchedulesEntity.builder()
                .productManager(productManager)
                .products(product)
                .dateStart(mpsRequest.getDateStart())
                .dateEnd(mpsRequest.getDateEnd())
                .quantity(mpsRequest.getQuantity())
                .requireTime(mpsRequest.getRequireTime())
                .durationHour(mpsRequest.getDurationHour())
                .effortHour(mpsRequest.getEffortHour())
                .build();
        masterProductionSchedulesRepository.save(mps);
    }
}

