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
public class MPSRequest {

    private Long product_manager_ID;

    private Long productId;

    private Date dateStart;

    private Date dateEnd;

    private Integer quantity;

    private Float requireTime;

    private Float durationHour;

    private Float effortHour;

    private Float in_progress;

}
