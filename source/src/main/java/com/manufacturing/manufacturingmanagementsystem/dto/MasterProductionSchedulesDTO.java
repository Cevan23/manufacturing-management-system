package com.manufacturing.manufacturingmanagementsystem.dto;

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
    private Long productManagerId;
    private Date dateStart;
    private Date dateEnd;
    private Integer quantity;
    private Float requireTime;
    private Float durationHour;
    private Float effortHour;

}
