package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Material;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the Material response
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MaterialResponse {

        private Long materialId;

        private String materialName;

        private String materialUnit;

        private Double materialPrice;

        private Double materialVolume;

        private Integer materialQuantity;

}
