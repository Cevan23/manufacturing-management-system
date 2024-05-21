package com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SuggestMPSMonthlyRequest {

    private Long productId;

    private Date month;
}
