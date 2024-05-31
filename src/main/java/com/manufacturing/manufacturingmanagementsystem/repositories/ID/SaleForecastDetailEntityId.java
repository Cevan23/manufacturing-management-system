package com.manufacturing.manufacturingmanagementsystem.repositories.ID;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
// Author: Pham Van Cao
// this class is used to handle the SaleForecastDetailEntityId response
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
public class SaleForecastDetailEntityId implements Serializable {
    private Long productId;
    private Long saleForecastId;
}
