package com.manufacturing.manufacturingmanagementsystem.dtos.responses.WorkOrder;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderRequest;
import lombok.*;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the WorkOrderDetail response
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkOrderDetailResponse {

    private WorkOrderRequest workOrder;

    private List<WorkOrderDetailRequest> workOrderDetails;
}
