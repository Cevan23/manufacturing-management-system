package com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BOMRequest {

    private Long productManagerId;

    @JsonProperty("BOMName")
    private String BOMName;

    @JsonProperty("BOMStatus")
    private String BOMStatus;

    private Float timeProduction;

    private String unit;

    private Double totalPrice;

    private Double sellPrice;

    private List<BOMDetailRequest> bomDetails;
}
