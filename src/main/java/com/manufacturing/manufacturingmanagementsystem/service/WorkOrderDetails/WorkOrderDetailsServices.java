package com.manufacturing.manufacturingmanagementsystem.service.WorkOrderDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.models.MasterProductionSchedulesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.WorkOrderDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.MasterProductionSchedulesRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.WorkOrderDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.WorkOrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// Author: Pham Van Cao
// this class is used to implement the methods defined in IWorkOrderDetailsServices.java
@Service
@AllArgsConstructor
public class WorkOrderDetailsServices implements IWorkOrderDetailsServices {

    private final WorkOrderDetailsRepository workOrderDetailsRepository;
    private final MasterProductionSchedulesRepository masterProductionSchedulesRepository;
    private final UsersRepository usersRepository;
    private final WorkOrdersRepository workOrdersRepository;
    // this method is used to create a new work order detail
    @Override
    public void createWorkOrderDetails(WorkOrderDetailRequest workOrderDetailRequest) {
        WorkOrdersEntity workOrder = workOrdersRepository.findById(workOrderDetailRequest.getWorkOrderId()).orElse(null);
        if(workOrder == null) return;
        MasterProductionSchedulesEntity masterProductionSchedule = masterProductionSchedulesRepository.findById(workOrderDetailRequest.getMasterProductionScheduleId()).orElse(null);
        if(masterProductionSchedule == null) return;
        WorkOrderDetailsEntity workOrderDetailsEntity = WorkOrderDetailsEntity.builder()
                .id(new WorkOrderDetailEntityId(workOrderDetailRequest.getMasterProductionScheduleId(), workOrderDetailRequest.getWorkOrderId()))
                .workOrder(workOrder)
                .masterProductionSchedule(masterProductionSchedule)
                .actualProduction(workOrderDetailRequest.getActualProduction())
                .actualProductionPrice(workOrderDetailRequest.getActualProductionPrice())
                .faultyProducts(workOrderDetailRequest.getFaultyProducts())
                .faultyProductPrice(workOrderDetailRequest.getFaultyProductPrice())
                .note(workOrderDetailRequest.getNote())
                .projectedProduction(workOrderDetailRequest.getProjectedProduction())
                .build();
        workOrderDetailsRepository.save(workOrderDetailsEntity);
    }
    // this method is used to update a work order detail
    @Override
    public void updateWorkOrderDetails(WorkOrderDetailRequest workOrderDetailRequest) {
        WorkOrdersEntity workOrder = workOrdersRepository.findById(workOrderDetailRequest.getWorkOrderId()).orElse(null);
        if(workOrder == null) return;
        MasterProductionSchedulesEntity masterProductionSchedule = masterProductionSchedulesRepository.findById(workOrderDetailRequest.getMasterProductionScheduleId()).orElse(null);
        if(masterProductionSchedule == null) return;

        Optional<WorkOrderDetailsEntity> workOrderDetailsEntity = workOrderDetailsRepository.findById(new WorkOrderDetailEntityId(workOrderDetailRequest.getMasterProductionScheduleId(), workOrderDetailRequest.getWorkOrderId()));
        if(workOrderDetailsEntity.isPresent()) {
            workOrderDetailsEntity.get().setWorkOrder(workOrder);
            workOrderDetailsEntity.get().setMasterProductionSchedule(masterProductionSchedule);
            workOrderDetailsEntity.get().setActualProduction(workOrderDetailRequest.getActualProduction());
            workOrderDetailsEntity.get().setActualProductionPrice(workOrderDetailRequest.getActualProductionPrice());
            workOrderDetailsEntity.get().setFaultyProducts(workOrderDetailRequest.getFaultyProducts());
            workOrderDetailsEntity.get().setFaultyProductPrice(workOrderDetailRequest.getFaultyProductPrice());
            workOrderDetailsEntity.get().setNote(workOrderDetailRequest.getNote());
            workOrderDetailsEntity.get().setProjectedProduction(workOrderDetailRequest.getProjectedProduction());
            workOrderDetailsRepository.save(workOrderDetailsEntity.get());
        }
    }
    // this method is used to delete a work order detail
    @Override
    public void deleteWorkOrderDetails(Long workOrderId, Long masterProductionScheduleId) {

        if(workOrderId != null && masterProductionScheduleId != null) workOrderDetailsRepository.deleteByWorkOrderIdAndMasterProductionScheduleId(workOrderId, masterProductionScheduleId);
    }
    // this method is used to get all work order details of a work order
    @Override
    public List<WorkOrderDetailsEntity> getWorkOrderDetailsOfWO(Long id) {
        if(id != null) {
            return workOrderDetailsRepository.findByWorkOrderId(id);
        }
        return null;
    }
    // this method is used to get all work order details
    @Override
    public List<WorkOrderDetailsEntity> getAllWorkOrderDetails() {
        return workOrderDetailsRepository.findAll();
    }
    // this method is used to sum the projected production by master production schedule
    @Override
    public Integer sumProjectedProductionByMasterProductionSchedule(Long masterProductionScheduleId) {
        if(masterProductionScheduleId != null) {
            return workOrderDetailsRepository.sumProjectedProductionByMasterProductionSchedule(masterProductionScheduleId);
        }
        return null;
    }
}
