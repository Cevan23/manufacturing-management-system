package com.manufacturing.manufacturingmanagementsystem.models;
import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "BOMs")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BOMsEntity extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    UsersEntity productManager;

    @Column(name = "name")
    private String name;

    @Column(name = "BOM_status",columnDefinition = "varchar(255) check (BOM_status in ('PENDING','CHECK_PRICE', 'FINISH')) ")
    private String BOMstatus;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "time_production")
    private Float timeProduction;

    @Column(name = "unit",columnDefinition = "varchar(255) check (unit in ('g', 'kg', 'amount','meter','liter'))")
    private String unit;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "sell_price")
    private Double sellPrice;

}
