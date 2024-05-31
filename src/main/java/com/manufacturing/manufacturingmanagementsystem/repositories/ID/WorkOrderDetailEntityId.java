package com.manufacturing.manufacturingmanagementsystem.repositories.ID;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
// Author: Pham Van Cao
// this class is used to handle the WorkOrderDetailEntityId response
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WorkOrderDetailEntityId  implements Serializable {
    private Long masterProductionScheduleId;
    private Long workOrderId;
}
