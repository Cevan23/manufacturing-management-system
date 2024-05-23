package com.manufacturing.manufacturingmanagementsystem.service.WorkOrders;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.WorkOrder.WorkOrderResponse;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.WorkOrdersRepository;
import com.manufacturing.manufacturingmanagementsystem.service.WorkOrderDetails.WorkOrderDetailsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkOrdersServices implements IWorkOrdersServices {

    private final WorkOrdersRepository workOrdersRepository;
    private final UsersRepository usersRepository;
    private final WorkOrderDetailsServices workOrderDetailsServices;

    @Override
    @Transactional
    public Long createWorkOrder(WorkOrderRequest workOrderRequest) {
        if(workOrderRequest.getProductManagerID() == null) {
            return null;
        }
        var productManager = usersRepository.findById(workOrderRequest.getProductManagerID()).orElseThrow();
        Date dateStart = workOrderRequest.getDateStart();
        Date dateEnd = workOrderRequest.getDateEnd();
        WorkOrdersEntity workOrder = WorkOrdersEntity.builder()
                .productManager(productManager)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .workOrderStatus(workOrderRequest.getWorkOrderStatus())
                .build();
        WorkOrdersEntity workOrderNew  = workOrdersRepository.save(workOrder);
        return workOrderNew.getId();
    }

    @Override
    public void updateWorkOrder(WorkOrderRequest workOrderRequest) {
        if(workOrderRequest == null) {
            return;
        }
        if(workOrderRequest.getId() == null) {
            return;
        }
        Optional<WorkOrdersEntity> workOrder = workOrdersRepository.findById(workOrderRequest.getId());
        if(workOrder.isEmpty()) {
            return;
        }
        if(workOrderRequest.getProductManagerID() != null) {
            Optional<UsersEntity> productManager = usersRepository.findById(workOrderRequest.getProductManagerID());
            if(productManager.isPresent()) {
                workOrder.get().setProductManager(productManager.get());
            }
            workOrder.get().setDateStart(workOrderRequest.getDateStart());
            workOrder.get().setDateEnd(workOrderRequest.getDateEnd());
            workOrder.get().setWorkOrderStatus(workOrderRequest.getWorkOrderStatus());
            workOrdersRepository.save(workOrder.get());
        }

    }

    @Override
    public void updateWorkOrderStatus(Long id, String status) {
        if(id != null) {
            Optional<WorkOrdersEntity> workOrder = workOrdersRepository.findById(id);
            if(workOrder.isPresent()) {
                workOrder.get().setWorkOrderStatus(status);
                workOrdersRepository.save(workOrder.get());
            }
        }
    }

    @Override
    public void deleteWorkOrder(Long id) {
        if(id != null) {
            workOrdersRepository.deleteById(id);
        }
    }

    @Override
    public List<WorkOrdersEntity> getAllWorkOrdersOfPM(Long id) {
        if(id != null) {
            return workOrdersRepository.findByProductManagerId(id);
        } else {
            return null;
        }
    }

    @Override
    public List<WorkOrdersEntity> getAllWorkOrders() {
        return workOrdersRepository.findAll();
    }

    @Override
    public WorkOrderResponse getWorkOrderById(Long id) {
        if(id != null) {
            Optional<WorkOrdersEntity> workOrder = workOrdersRepository.findById(id);
            List<WorkOrderDetailsEntity> workOrderDetails = workOrderDetailsServices.getWorkOrderDetailsOfWO(id);
            if(workOrder.isPresent()) {
                return WorkOrderResponse.builder()
                        .workOrder(workOrder.get())
                        .workOrderDetails(workOrderDetails)
                        .build();
            }
        }
        return null;
    }
}

