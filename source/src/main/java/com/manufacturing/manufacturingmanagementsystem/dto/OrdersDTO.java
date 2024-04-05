package com.manufacturing.manufacturingmanagementsystem.dto;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

    private Long id;

    private Long customerId;

    private Long accountantId;

    private Date dateStart;

    private Date dateEnd;

    private Integer kindOrder;

    private Float totalPrice;

    private Integer orderStatus;
}
