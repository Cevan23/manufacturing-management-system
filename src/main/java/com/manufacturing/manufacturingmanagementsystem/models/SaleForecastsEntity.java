package com.manufacturing.manufacturingmanagementsystem.models;
import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;

import java.util.Date;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecastsEntity response
@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "sale_forecasts")
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountants_id")
    private UsersEntity accountant;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;
}
