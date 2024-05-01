package com.manufacturing.manufacturingmanagementsystem.models;

import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.BOMDetailEntityId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "BOM_details")
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
    private BOMsEntity BOM;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId("materialId")
    private MaterialsEntity material;

    @Column(name = "quantity")
    private Integer quantity;

}
