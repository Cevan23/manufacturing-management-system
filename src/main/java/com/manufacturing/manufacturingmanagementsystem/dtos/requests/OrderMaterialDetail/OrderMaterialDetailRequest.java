package com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderMaterialDetail;

import lombok.*;

import java.util.List;
// Author: Nguyen Cao Nhan
// this class is used to handle the OrderMaterialDetail request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderMaterialDetailRequest {
    private Long oid;
    private List<Long> mids;
    private List<Integer> quantities;
}