package com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderMaterialDetail;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the OrderMaterialDetail request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderMaterialDetailUpdateRequest {
    private Long oid;
    private Long mid;
    private Integer quantity;
}
