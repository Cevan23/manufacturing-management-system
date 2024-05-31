package com.manufacturing.manufacturingmanagementsystem.models;
import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
// Author: Pham Van Cao
// this class is used to handle the CategoriesEntity response
@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "customers")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomersEntity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;
}
