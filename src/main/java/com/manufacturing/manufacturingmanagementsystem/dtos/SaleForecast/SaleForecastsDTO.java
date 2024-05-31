package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;
import lombok.*;

import java.util.Date;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecastsDTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleForecastsDTO {

    private Long id;

    private Long accountantId;

    private Date dateStart;

    private Date dateEnd;
}
