package com.manufacturing.manufacturingmanagementsystem.service.MasterProductionSchedules;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.MasterProductionSchedulesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.MasterProductionSchedulesRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.ProductsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MPSServices implements IMPSServices {

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
                .in_progress(0.0f)
                .build();
        masterProductionSchedulesRepository.save(mps);
    }

    @Override
    public void updateMPS(MPSUpdateRequest mpsRequest) {
        if(mpsRequest.getMpsID() != null){
            Optional<MasterProductionSchedulesEntity> mps = masterProductionSchedulesRepository.findById(mpsRequest.getMpsID());
            if(mps.isEmpty()){
                throw new AppException(ErrorCode.NOT_FOUND);
            } else {
              Optional<ProductsEntity> product = productsRepository.findById(mpsRequest.getProductId());
              Optional<UsersEntity> productManager = usersRepository.findById(mpsRequest.getProduct_manager_ID());
              if(product.isPresent() && productManager.isPresent()) {
                  mps.get().setProducts(product.get());
                  mps.get().setProductManager(productManager.get());
                  mps.get().setDateEnd(mpsRequest.getDateEnd());
                  mps.get().setDateStart(mpsRequest.getDateEnd());
                  mps.get().setEffortHour(mpsRequest.getEffortHour());
                  mps.get().setDurationHour(mpsRequest.getDurationHour());
                  mps.get().setQuantity(mpsRequest.getQuantity());
                  mps.get().setRequireTime(mpsRequest.getRequireTime());
                  mps.get().setIn_progress(mpsRequest.getIn_progress());
                  masterProductionSchedulesRepository.save(mps.get());
              }
            }


        }
    }

    @Override
    public  List<MasterProductionSchedulesEntity> getAllMPSofPM(Long pmID){
        try {
            return masterProductionSchedulesRepository.findAllByProductManager_Id(pmID);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public  List<MasterProductionSchedulesEntity> getALl(){
        try {
            return masterProductionSchedulesRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteMPS(Long id){
        if(id == null){
            throw new AppException(ErrorCode.BAD_REQUEST);
        } else {
            Optional<MasterProductionSchedulesEntity> mps = masterProductionSchedulesRepository.findById(id);
            mps.ifPresent(masterProductionSchedulesRepository::delete);
        }
    }

    public MasterProductionSchedulesEntity findMPSbyID(Long id) {
        MasterProductionSchedulesEntity mps = new MasterProductionSchedulesEntity();
        if(id != null){
            Optional<MasterProductionSchedulesEntity> mpsOptional = masterProductionSchedulesRepository.findById(id);
            if(mpsOptional.isEmpty()){
                return null;
            } else {
               mps = mpsOptional.get();
            }

        }

        return mps;
    }
}

