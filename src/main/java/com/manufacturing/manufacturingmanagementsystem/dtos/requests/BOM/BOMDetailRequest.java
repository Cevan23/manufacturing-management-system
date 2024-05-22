package com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Material.MaterialRequest;
import lombok.*;

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
