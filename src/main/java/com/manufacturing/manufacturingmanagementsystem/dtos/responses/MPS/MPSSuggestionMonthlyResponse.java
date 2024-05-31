package com.manufacturing.manufacturingmanagementsystem.dtos.responses.MPS;

import lombok.*;

import java.sql.Date;
// Author: Pham Van Cao
// this class is used to handle the MPS Suggestion Monthly response
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MPSSuggestionMonthlyResponse {

    private Long productId;

    private String productName;

    private Integer quantity;

    private Date dateStart;

    private Date dateEnd;

    private Float requireTime;

    private Float durationHour;

    private Float effortHour;

    private Float in_progress;
}
