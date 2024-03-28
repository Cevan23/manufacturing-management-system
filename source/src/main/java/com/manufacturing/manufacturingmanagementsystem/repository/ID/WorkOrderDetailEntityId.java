package com.manufacturing.manufacturingmanagementsystem.repository.ID;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WorkOrderDetailEntityId  implements Serializable {
    private Long masterProductionScheduleId;
    private Long workOrderId;
}
