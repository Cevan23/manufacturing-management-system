package com.manufacturing.manufacturingmanagementsystem.models;

import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.OrderProductDetailEntityId;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
// Author: Nguyen Cao Nhan
// this class is used to handle the OrderProductDetailsEntity response
@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "order_product_details")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderProductDetailsEntity extends Auditable<String> {

    @EmbeddedId
    private OrderProductDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("orderId")
    private OrdersEntity order;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("productId")
    private ProductsEntity product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_unit_price")
    private Float totalUnitPrice;
}
