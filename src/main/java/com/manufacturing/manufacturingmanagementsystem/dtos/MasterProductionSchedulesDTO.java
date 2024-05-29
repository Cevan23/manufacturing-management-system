package com.manufacturing.manufacturingmanagementsystem.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterProductionSchedulesDTO {

    private Long id;
    private Long productsId;
    private Double productPrice;
    private Long productManagerId;
    private Date dateStart;
    private Date dateEnd;
    private Integer quantity;
    private Float requireTime;
    private Float durationHour;
    private Float effortHour;
    private Float inProgress;

}
