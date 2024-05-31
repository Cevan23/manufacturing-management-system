package com.manufacturing.manufacturingmanagementsystem.dtos.requests.Material;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the material request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MaterialRequest {

    private String materialName;

    private String materialUnit;

    private Double materialPrice;

    private Double materialVolume;
}
