package com.manufacturing.manufacturingmanagementsystem.service.WorkOrderDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.WorkOrderDetailEntityId;

import java.util.List;
// Author: Pham Van Cao
// this interface is used to define the methods that will be implemented in WorkOrderDetailsServices.java
public interface IWorkOrderDetailsServices {

    void createWorkOrderDetails(WorkOrderDetailRequest workOrderDetailRequest);

    void updateWorkOrderDetails(WorkOrderDetailRequest workOrderDetailRequest);

    void deleteWorkOrderDetails(Long workOrderId, Long masterProductionScheduleId);

    List<WorkOrderDetailsEntity> getWorkOrderDetailsOfWO(Long workOrderId);

    List<WorkOrderDetailsEntity> getAllWorkOrderDetails();

    Integer sumProjectedProductionByMasterProductionSchedule(Long masterProductionScheduleId);

}
