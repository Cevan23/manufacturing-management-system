package com.manufacturing.manufacturingmanagementsystem.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
// Author: Pham Van Cao
// this class is used to handle the InvalidatedTokenEntity response
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invalidated_token")
public class InvalidatedTokenEntity {
    @Id
    private String id;
    private Date expirationTime;
}
