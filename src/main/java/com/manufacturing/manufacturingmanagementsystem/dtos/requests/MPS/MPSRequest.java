package com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MPSRequest {

    private Long PMId;

    private Long productId;

    private Date dateStart;

    private Date dateEnd;

    private Integer quantity;

    private Float requireTime;

    private Float durationHour;

    private Float effortHour;

}
