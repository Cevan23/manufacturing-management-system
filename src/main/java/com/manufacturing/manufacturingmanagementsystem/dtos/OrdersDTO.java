package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;

import java.util.Date;
// Author: Pham Van Cao
// this class is used to handle the OrdersDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

    private Long id;

    private Long customerId;

    private Long accountantId;

    private Date dateStart;

    private Date dateEnd;

    private String kindOrder;

    private Float totalPrice;

    private String orderStatus;
}
