package com.manufacturing.manufacturingmanagementsystem.model;
import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "sale_forecast")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleForecastsEntity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountants_id")
    private UsersEntity accountant;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;
}
