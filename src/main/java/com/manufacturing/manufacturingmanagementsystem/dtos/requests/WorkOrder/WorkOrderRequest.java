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

    private Long id;

    private Long productManagerID;

    private Date dateStart;

    private Date dateEnd;

    private String workOrderstatus;
}
