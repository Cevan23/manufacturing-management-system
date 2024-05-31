package com.manufacturing.manufacturingmanagementsystem.models;

import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderMaterialDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderProductDetailEntityId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
// Author: Nguyen Cao Nhan
// this class is used to handle the OrderMaterialDetailsEntity response
@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "order_material_details")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderMaterialDetailsEntity extends Auditable<String> {

    @EmbeddedId
    private OrderMaterialDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("orderId")
    private OrdersEntity order;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("materialId")
    private MaterialsEntity material;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_unit_price")
    private Float totalUnitPrice;
}
