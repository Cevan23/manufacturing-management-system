package com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecastDetail;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SFDInsertRequest {
    private Long sid;
    private List<Long> pids;
    private List<Integer> quantities;
}
