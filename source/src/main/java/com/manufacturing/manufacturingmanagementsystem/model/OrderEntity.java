package com.manufacturing.manufacturingmanagementsystem.model;
import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import com.manufacturing.manufacturingmanagementsystem.model.CustomersEntity;
import java.sql.Date;

@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "order")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountants_id")
    private UsersEntity accountant;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "kind_order")
    private Integer kindOrder;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "order_status")
    private Integer orderStatus;
}
