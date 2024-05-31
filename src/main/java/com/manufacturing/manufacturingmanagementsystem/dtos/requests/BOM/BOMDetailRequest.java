package com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Material.MaterialRequest;
import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the BOM detail request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BOMDetailRequest {

    private Long BOMId;

    private MaterialRequest material;

    private Integer quantity;

    private Float totalUnitPrice;
}
