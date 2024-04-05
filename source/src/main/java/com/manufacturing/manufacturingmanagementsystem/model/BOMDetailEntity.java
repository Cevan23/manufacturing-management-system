package com.manufacturing.manufacturingmanagementsystem.model;

import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repository.ID.BOMDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repository.ID.InventoriesDetailEntityId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "BOM_detail")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BOMDetailEntity extends Auditable<String>{

    @EmbeddedId
    private BOMDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("BOMId")
    private BOMEntity BOMId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("materialId")
    private MaterialEntity materialId;

    @Column(name = "quantity")
    private Integer quantity;

}
