package com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order;

import lombok.*;

import java.util.Date;
// Author: Nguyen Cao Nhan
// this class is used to handle the Order request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderUpdateRequest {
    private Long id;
    private Date dateStart;
    private Date dateEnd;
    private String orderStatus;
}