package com.manufacturing.manufacturingmanagementsystem.dto;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleForecastsDTO {

    private Long id;

    private Long accountantId;

    private Date dateStart;

    private Date dateEnd;
}
