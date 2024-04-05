package com.manufacturing.manufacturingmanagementsystem.model;

import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repository.ID.BOMDetailEntityId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "BOM_detail")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BOMDetailsEntity extends Auditable<String>{

    @EmbeddedId
    private BOMDetailEntityId id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("BOMId")
    private BOMsEntity BOMId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("materialId")
    private MaterialsEntity materialId;

    @Column(name = "quantity")
    private Integer quantity;

}
