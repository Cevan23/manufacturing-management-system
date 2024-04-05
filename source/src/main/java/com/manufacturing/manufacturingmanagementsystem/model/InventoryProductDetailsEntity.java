package com.manufacturing.manufacturingmanagementsystem.model;

import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryProductDetailEntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "inventory_product_details")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryProductDetailsEntity extends Auditable<String> {

    @EmbeddedId
    private InventoryProductDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("inventoryId")
    private InventoriesEntity inventoryId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("productId")
    private ProductsEntity productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "safety_stock_amount")
    private Integer safetyStockAmount;

}
