package com.manufacturing.manufacturingmanagementsystem.service.MasterProductionSchedules;

import com.manufacturing.manufacturingmanagementsystem.dtos.MasterProductionSchedulesDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.MPS.MPSResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.MPS.MPSSuggestionMonthlyResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.MasterProductionSchedulesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.MasterProductionSchedulesRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.ProductsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.SaleForecastDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Author: Pham Van Cao
// this class is used to handle the MasterProductionSchedulesServices response
@Service
@AllArgsConstructor
public class MasterProductionSchedulesServices implements IMasterProductionSchedulesServices {

    private final MasterProductionSchedulesRepository masterProductionSchedulesRepository;
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    private final SaleForecastDetailsRepository saleForecastDetailsRepository;

    // this service is used to create MPS
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
    // this service is used to update MPS
    @Override
    public void updateMPS(MPSUpdateRequest mpsRequest) {
        System.out.println("mpsRequest = " + mpsRequest);
        if(mpsRequest.getMpsID() != null){
            Optional<MasterProductionSchedulesEntity> mps = masterProductionSchedulesRepository.findByMPSId(mpsRequest.getMpsID());
            System.out.println("mps = " + mps.toString());
            if(mps.isEmpty()){
                throw new AppException(ErrorCode.NOT_FOUND);
            } else {
              Optional<ProductsEntity> product = productsRepository.findById(mpsRequest.getProductId());
              Optional<UsersEntity> productManager = usersRepository.findById(mpsRequest.getProduct_manager_ID());
              if(product.isPresent() && productManager.isPresent()) {
                  Date startDate = mpsRequest.getDateStart();
                  Date endDate = mpsRequest.getDateEnd();
                  System.out.println("startDate = " + startDate + " endDate = " + endDate);
                  mps.get().setProducts(product.get());
                  mps.get().setProductManager(productManager.get());
                  mps.get().setDateStart(startDate);
                  mps.get().setDateEnd(endDate);
                  mps.get().setEffortHour(mpsRequest.getEffortHour());
                  mps.get().setDurationHour(mpsRequest.getDurationHour());
                  mps.get().setQuantity(mpsRequest.getQuantity());
                  mps.get().setRequireTime(mpsRequest.getRequireTime());
                  mps.get().setIn_progress(mpsRequest.getIn_progress());
                  System.out.println("mps.get() = " + mps);
                  masterProductionSchedulesRepository.save(mps.get());
              }
            }


        }
    }
    // this service is used to get MPS by id
    @Override
    public MPSResponse getById(Long id){
        if(id == null){
            throw new AppException(ErrorCode.BAD_REQUEST);
        } else {
            Optional<MasterProductionSchedulesEntity> mps = masterProductionSchedulesRepository.findByMPSId(id);
            if(mps.isEmpty()){
                throw new AppException(ErrorCode.NOT_FOUND);
            } else {
                return MPSResponse.builder()
                        .mpsID(mps.get().getId())
                        .product_manager_ID(mps.get().getProductManager().getId())
                        .productManagerName(mps.get().getProductManager().getFullName())
                        .productName(mps.get().getProducts().getName())
                        .productId(mps.get().getProducts().getId())
                        .dateStart(mps.get().getDateStart())
                        .dateEnd(mps.get().getDateEnd())
                        .quantity(mps.get().getQuantity())
                        .requireTime(mps.get().getRequireTime())
                        .durationHour(mps.get().getDurationHour())
                        .effortHour(mps.get().getEffortHour())
                        .in_progress(mps.get().getIn_progress())
                        .build();
            }
        }
    }
    // this service is used to get all MPS of PM
    @Override
    public  List<MPSResponse> getAllMPSofPM(Long pmID){
        try {
            List<MasterProductionSchedulesEntity> mps = masterProductionSchedulesRepository.findAllByProductManager_Id(pmID);
            List<MPSResponse> mpsResponses = new ArrayList<>();
            for (MasterProductionSchedulesEntity m : mps) {
                MPSResponse mpsResponse = MPSResponse.builder()
                        .mpsID(m.getId())
                        .product_manager_ID(m.getProductManager().getId())
                        .productManagerName(m.getProductManager().getFullName())
                        .productName(m.getProducts().getName())
                        .productId(m.getProducts().getId())
                        .dateStart(m.getDateStart())
                        .dateEnd(m.getDateEnd())
                        .quantity(m.getQuantity())
                        .requireTime(m.getRequireTime())
                        .durationHour(m.getDurationHour())
                        .effortHour(m.getEffortHour())
                        .in_progress(m.getIn_progress())
                        .build();
                mpsResponses.add(mpsResponse);
            }
            return mpsResponses;
        } catch (Exception e) {
            return null;
        }
    }
    // this service is used to get all MPS
    @Override
    public  List<MPSResponse> getALl(){
        try {
            List<MasterProductionSchedulesEntity> mps = masterProductionSchedulesRepository.findAll();
            List<MPSResponse> mpsResponses = new ArrayList<>();
            for (MasterProductionSchedulesEntity m : mps) {
                MPSResponse mpsResponse = MPSResponse.builder()
                        .mpsID(m.getId())
                        .product_manager_ID(m.getProductManager().getId())
                        .productManagerName(m.getProductManager().getFullName())
                        .productName(m.getProducts().getName())
                        .productId(m.getProducts().getId())
                        .productPrice(m.getProducts().getPrice())
                        .dateStart(m.getDateStart())
                        .dateEnd(m.getDateEnd())
                        .quantity(m.getQuantity())
                        .requireTime(m.getRequireTime())
                        .durationHour(m.getDurationHour())
                        .effortHour(m.getEffortHour())
                        .in_progress(m.getIn_progress())
                        .build();
                mpsResponses.add(mpsResponse);
            }
            return mpsResponses;
        } catch (Exception e) {
            return null;
        }
    }
    // this service is used to delete MPS
    @Override
    public void deleteMPS(Long id){
        if(id == null){
            throw new AppException(ErrorCode.BAD_REQUEST);
        } else {
            Optional<MasterProductionSchedulesEntity> mps = masterProductionSchedulesRepository.findById(id);
            mps.ifPresent(masterProductionSchedulesRepository::delete);
        }
    }
    // this service is used to suggest MPS monthly
    @Override
    public MPSSuggestionMonthlyResponse suggestMPSMonthly(Long productId, Date month){

        
        return null;
    }
    // this service is used to get all MPS by in progress
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
    // this service is used to get all MPS by in progress
    @Override
    public List<MasterProductionSchedulesDTO> getAllMPSbyInProgress(Float inProgress){
        try {
            List<MasterProductionSchedulesEntity> mps = masterProductionSchedulesRepository.findAllByInProgress(inProgress);
            if(mps.isEmpty()){
                return null;
            }
            List<MasterProductionSchedulesDTO> mpsResponses = new ArrayList<>();
            for (MasterProductionSchedulesEntity m : mps) {
                MasterProductionSchedulesDTO mpsResponse = new MasterProductionSchedulesDTO();
                mpsResponse.setId(m.getId());
                mpsResponse.setProductManagerId(m.getProductManager().getId());
                mpsResponse.setProductsId(m.getProducts().getId());
                mpsResponse.setDateStart(m.getDateStart());
                mpsResponse.setDateEnd(m.getDateEnd());
                mpsResponse.setQuantity(m.getQuantity());
                mpsResponse.setRequireTime(m.getRequireTime());
                mpsResponse.setDurationHour(m.getDurationHour());
                mpsResponse.setEffortHour(m.getEffortHour());
                mpsResponses.add(mpsResponse);
            }
            return mpsResponses;
        } catch (Exception e) {
            return null;
        }

    }
}

