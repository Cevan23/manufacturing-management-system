package com.manufacturing.manufacturingmanagementsystem.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Author: Pham Van Cao
// this class is used to handle the InventoryDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor

public class InventoryDTO {

    private String name;

    private String address;

    private Float maxVolume;
}
