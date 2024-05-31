package com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS;

import lombok.*;

import java.sql.Date;
// Author: Pham Van Cao
// this class is used to handle the MPS request
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
