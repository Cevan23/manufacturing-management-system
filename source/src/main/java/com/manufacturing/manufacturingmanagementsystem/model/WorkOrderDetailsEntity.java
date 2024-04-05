package com.manufacturing.manufacturingmanagementsystem.model;
import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repository.ID.WorkOrderDetailEntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "work_order_details")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkOrderDetailsEntity extends Auditable<String> {

    @EmbeddedId
    private WorkOrderDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("workOrderId")
    private WorkOrdersEntity workOrderId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("masterProductionScheduleId")
    private MasterProductionSchedulesEntity masterProductionSchedule;

    @Column(name = "note")
    private String note;

    @Column(name = "projected_production")
    private Integer projectedProduction;

    @Column(name = "actual_production")
    private Integer actualProduction;

    @Column(name = "faulty_products")
    private Integer faultyProducts;

    @Column(name = "actual_production_price")
    private Double actualProductionPrice;

    @Column(name = "faulty_products_price")
    private Double faultyProductPrice;
}
