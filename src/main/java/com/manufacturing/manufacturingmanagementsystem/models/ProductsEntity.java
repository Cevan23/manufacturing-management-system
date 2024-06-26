package com.manufacturing.manufacturingmanagementsystem.models;
import com.manufacturing.manufacturingmanagementsystem.models.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
// Author: Pham Van Cao
// this class is used to handle the ProductsEntity response
@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "products")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductsEntity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private CategoriesEntity category;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bom_id")
    private BOMsEntity bom;

    @Column(name = "name")
    private String name;

    @Column(name = "unit",columnDefinition = "varchar(255) check (unit in ('g', 'kg', 'amount','meter','liter'))")
    private String unit;

    @Column(name = "price")
    private Double price;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "volume")
    private Double volume;

}
