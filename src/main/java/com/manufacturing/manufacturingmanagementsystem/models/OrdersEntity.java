package com.manufacturing.manufacturingmanagementsystem.models;
import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "orders")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdersEntity extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountants_id")
    private UsersEntity accountant;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "kind_order")
    private String kindOrder;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "order_status")
    private String orderStatus;
}
