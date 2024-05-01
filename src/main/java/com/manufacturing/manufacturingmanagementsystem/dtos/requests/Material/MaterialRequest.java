package com.manufacturing.manufacturingmanagementsystem.dtos.requests.Material;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MaterialRequest {

    private String nameMaterial;

    private String unitMaterial;

    private Double priceMaterial;

    private Double volumeMaterial;
}
