package com.manufacturing.manufacturingmanagementsystem.models;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
// Author: Pham Van Cao
// this class is used to handle the PermissionsEntity response
@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "permissions")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionsEntity {
    @Id
    String name;
    String description;
}
