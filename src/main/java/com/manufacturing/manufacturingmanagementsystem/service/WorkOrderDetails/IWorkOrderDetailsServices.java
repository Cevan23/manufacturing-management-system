package com.manufacturing.manufacturingmanagementsystem.service.WorkOrderDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.WorkOrderDetailEntityId;

import java.util.List;

public interface IWorkOrderDetailsServices {

    void createWorkOrderDetails(WorkOrderDetailRequest workOrderDetailRequest);

    void updateWorkOrderDetails(WorkOrderDetailRequest workOrderDetailRequest);

    void deleteWorkOrderDetails(Long workOrderId, Long masterProductionScheduleId);

    List<WorkOrderDetailsEntity> getWorkOrderDetailsOfWO(Long workOrderId);

    List<WorkOrderDetailsEntity> getAllWorkOrderDetails();



}
