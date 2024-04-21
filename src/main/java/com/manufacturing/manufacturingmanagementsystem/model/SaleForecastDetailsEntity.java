package com.manufacturing.manufacturingmanagementsystem.model;

import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.SaleForecastDetailEntityId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "sale_forecast_details")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleForecastDetailsEntity extends Auditable<String> {

    @EmbeddedId
    private SaleForecastDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("saleForecastId")
    private SaleForecastsEntity saleForecastId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("productId")
    private ProductsEntity productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "total_sale_price")
    private Float totalSalePrice;
}
