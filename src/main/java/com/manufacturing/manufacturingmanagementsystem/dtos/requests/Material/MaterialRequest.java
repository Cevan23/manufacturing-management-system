package com.manufacturing.manufacturingmanagementsystem.dtos.requests.Material;

import lombok.*;

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
