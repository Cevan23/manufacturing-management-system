package com.manufacturing.manufacturingmanagementsystem.models;
import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "master_production_schedules")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MasterProductionSchedulesEntity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ProductsEntity products;

    @ManyToOne(cascade = CascadeType.PERSIST)
    UsersEntity productManager;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "require_time")
    private Float requireTime;

    @Column(name = "duration_hour")
    private Float durationHour;

    @Column(name = "effort_hour")
    private Float effortHour;
}
