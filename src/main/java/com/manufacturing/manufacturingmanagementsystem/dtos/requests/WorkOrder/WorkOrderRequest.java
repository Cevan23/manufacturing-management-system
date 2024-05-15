package com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkOrderRequest {

    private Long product_manager_ID;

    private Date dateStart;

    private Date dateEnd;

    private Integer workOrderstatus;
}
