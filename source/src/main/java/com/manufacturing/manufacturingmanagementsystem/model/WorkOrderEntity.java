package com.manufacturing.manufacturingmanagementsystem.model;

import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "work_order")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkOrderEntity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_manager_id")
    private ProductManagerEntity productManager;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "work_order_status",  columnDefinition = "varchar(255) check (work_order_status in ('pending', 'processing', 'PMcheck','ACcheck'))")
    private Integer workOrderstatus;
}
