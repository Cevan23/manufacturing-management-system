package com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderProductDetail;

import lombok.*;

import java.util.List;
// Author: Nguyen Cao Nhan
// this class is used to handle the OrderProductDetail request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductDetailRequest {
    private Long oid;
    private List<Long> pids;
    private List<Integer> quantities;
}