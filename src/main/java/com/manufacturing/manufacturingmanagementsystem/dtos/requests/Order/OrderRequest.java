package com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order;

import com.manufacturing.manufacturingmanagementsystem.models.CustomersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;

// Author: Nguyen Cao Nhan
// this class is used to handle the Order request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderRequest {
    private Long accountants_id;
    private String name;
    private String contact;
    private Date dateStart;
    private Date dateEnd;
    private String kindOrder;
}