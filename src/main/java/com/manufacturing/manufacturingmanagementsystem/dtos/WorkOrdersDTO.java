package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;

import java.util.Date;
// Author: Pham Van Cao
// this class is used to handle the WorkOrdersDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrdersDTO {

    private Long id;

    private Long productManagerId;

    private Date dateStart;

    private Date dateEnd;

    private Integer workOrderStatus;
}