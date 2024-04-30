package com.manufacturing.manufacturingmanagementsystem.models;

import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryMaterialDetailEntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "inventory_material_details")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryMaterialDetailsEntity extends Auditable<String>  {

    @EmbeddedId
    private InventoryMaterialDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("inventoryId")
    private InventoriesEntity inventoryId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("materialId")
    private MaterialsEntity materialId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "safety_stock_amount")
    private Integer safetyStockAmount;

    @Column(name = "total_unit_price")
    private Float totalUnitPrice;

}
