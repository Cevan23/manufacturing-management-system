package com.manufacturing.manufacturingmanagementsystem.service.WorkOrders;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.WorkOrder.WorkOrderResponse;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrdersEntity;

import java.util.List;
// Author: Pham Van Cao
// this interface is used to define the methods that will be implemented in the WorkOrdersServices class
public interface IWorkOrdersServices {

    Long createWorkOrder(WorkOrderRequest workOrderRequest);

    void updateWorkOrder(WorkOrderRequest workOrderRequest);

    void deleteWorkOrder(Long id);

    void updateWorkOrderStatus(Long id, String status);

    List<WorkOrdersEntity> getAllWorkOrders();

    List<WorkOrdersEntity> getAllWorkOrdersOfPM(Long pmID);

    WorkOrderResponse getWorkOrderById(Long id);

    List<WorkOrdersEntity> getAllWorkOrdersStartingToday();
}

