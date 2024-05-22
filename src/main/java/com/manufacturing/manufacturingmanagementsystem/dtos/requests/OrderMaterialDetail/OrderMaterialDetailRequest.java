package com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderMaterialDetail;

import lombok.*;

import java.util.List;
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