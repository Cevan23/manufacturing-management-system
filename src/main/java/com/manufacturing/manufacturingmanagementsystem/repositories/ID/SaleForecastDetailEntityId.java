package com.manufacturing.manufacturingmanagementsystem.repositories.ID;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

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
