package com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecast;

import lombok.*;

import java.util.Date;

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