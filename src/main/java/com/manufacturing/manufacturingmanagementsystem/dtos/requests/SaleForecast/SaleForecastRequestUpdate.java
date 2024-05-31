package com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecast;

import lombok.*;

import java.util.Date;
// Author: Pham Van Cao
// this class is used to handle the SaleForecast request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SaleForecastRequestUpdate {
    private Date dateStart;
    private Date dateEnd;
}