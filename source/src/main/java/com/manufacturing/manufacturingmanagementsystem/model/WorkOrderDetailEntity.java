package com.manufacturing.manufacturingmanagementsystem.model;
import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repository.ID.InventoriesDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repository.ID.WorkOrderDetailEntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "work_order_detail")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkOrderDetailEntity extends Auditable<String> {

    @EmbeddedId
    private WorkOrderDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("workOrderId")
    private WorkOrderEntity workOrderId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("masterProductionScheduleId")
    private MasterProductionScheduleEntity masterProductionSchedule;

    @Column(name = "note")
    private String note;

    @Column(name = "finished_goods")
    private Integer finishedGoods;

    @Column(name = "faulty_products")
    private Integer faultyProducts;

    @Column(name = "finished_goods_price")
    private Double finishedGoodsPrice;

    @Column(name = "faulty_products_price")
    private Double faultyProductPrice;
}
