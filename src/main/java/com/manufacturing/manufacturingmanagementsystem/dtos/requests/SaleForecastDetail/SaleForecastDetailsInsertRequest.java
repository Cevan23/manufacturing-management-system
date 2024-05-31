package com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecastDetail;

import lombok.*;

import java.util.List;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecastDetail request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SaleForecastDetailsInsertRequest {
    private Long sid;
    private List<Long> pids;
    private List<Integer> quantities;
}
