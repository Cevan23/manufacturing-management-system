package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Material;

import lombok.*;

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
