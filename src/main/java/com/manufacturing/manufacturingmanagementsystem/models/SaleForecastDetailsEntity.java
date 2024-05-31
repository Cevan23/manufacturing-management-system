package com.manufacturing.manufacturingmanagementsystem.models;

import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.SaleForecastDetailEntityId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecastDetailsEntity response
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
    private SaleForecastsEntity saleForecast;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("productId")
    private ProductsEntity product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "total_sale_price")
    private Float totalSalePrice;
}
