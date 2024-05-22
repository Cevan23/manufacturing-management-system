package com.manufacturing.manufacturingmanagementsystem.service.WorkOrderDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.WorkOrderDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.WorkOrderDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkOrderDetailsServices implements IWorkOrderDetailsServices {

    private final WorkOrderDetailsRepository workOrderDetailsRepository;

    @Override
    public void createWorkOrderDetails(WorkOrderDetailRequest workOrderDetailRequest) {
        WorkOrderDetailsEntity workOrderDetailsEntity = WorkOrderDetailsEntity.builder()
                .id(new WorkOrderDetailEntityId(workOrderDetailRequest.getMasterProductionScheduleId(), workOrderDetailRequest.getWorkOrderId()))
                .actualProduction(workOrderDetailRequest.getActualProduction())
                .actualProductionPrice(workOrderDetailRequest.getActualProductionPrice())
                .faultyProducts(workOrderDetailRequest.getFaultyProducts())
                .faultyProductPrice(workOrderDetailRequest.getFaultyProductPrice())
                .note(workOrderDetailRequest.getNote())
                .projectedProduction(workOrderDetailRequest.getProjectedProduction())
                .build();
        workOrderDetailsRepository.save(workOrderDetailsEntity);
    }

    @Override
    public void updateWorkOrderDetails(WorkOrderDetailRequest workOrderDetailRequest) {
        Optional<WorkOrderDetailsEntity> workOrderDetailsEntity = workOrderDetailsRepository.findById(new WorkOrderDetailEntityId(workOrderDetailRequest.getMasterProductionScheduleId(), workOrderDetailRequest.getWorkOrderId()));
        if(workOrderDetailsEntity.isPresent()) {
            workOrderDetailsEntity.get().setActualProduction(workOrderDetailRequest.getActualProduction());
            workOrderDetailsEntity.get().setActualProductionPrice(workOrderDetailRequest.getActualProductionPrice());
            workOrderDetailsEntity.get().setFaultyProducts(workOrderDetailRequest.getFaultyProducts());
            workOrderDetailsEntity.get().setFaultyProductPrice(workOrderDetailRequest.getFaultyProductPrice());
            workOrderDetailsEntity.get().setNote(workOrderDetailRequest.getNote());
            workOrderDetailsEntity.get().setProjectedProduction(workOrderDetailRequest.getProjectedProduction());
            workOrderDetailsRepository.save(workOrderDetailsEntity.get());
        }
    }

    @Override
    public void deleteWorkOrderDetails(Long workOrderId, Long masterProductionScheduleId) {
        WorkOrderDetailEntityId id = new WorkOrderDetailEntityId(workOrderId, masterProductionScheduleId);
        if(id != null) workOrderDetailsRepository.deleteById(id);
    }

    @Override
    public List<WorkOrderDetailsEntity> getWorkOrderDetailsOfWO(Long id) {
        if(id != null) {
            return workOrderDetailsRepository.findByWorkOrderId(id);
        }
        return null;
    }

    @Override
    public List<WorkOrderDetailsEntity> getAllWorkOrderDetails() {
        return workOrderDetailsRepository.findAll();
    }
}
