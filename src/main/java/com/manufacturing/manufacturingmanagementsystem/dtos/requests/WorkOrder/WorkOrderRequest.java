package com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder;

import lombok.*;

import java.sql.Date;
// Author: Pham Van Cao
// this class is used to handle the WorkOrder request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkOrderRequest {

    private Long id;

    private Long productManagerID;

    private Date dateStart;

    private Date dateEnd;

    private String workOrderStatus;
}
