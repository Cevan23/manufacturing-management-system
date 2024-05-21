package com.manufacturing.manufacturingmanagementsystem.dtos.responses.MPS;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MPSResponse {

    private Long mpsID;

    private Long productId;

    private String productName;

    private Long categoryID;

    private String Category;

    private Long product_manager_ID;

    private String productManagerName;

    private java.sql.Date dateStart;

    private java.sql.Date dateEnd;

    private Integer quantity;

    private Float requireTime;

    private Float durationHour;

    private Float effortHour;

    private Float in_progress;
}
