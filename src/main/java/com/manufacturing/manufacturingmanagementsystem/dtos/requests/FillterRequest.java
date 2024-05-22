package com.manufacturing.manufacturingmanagementsystem.dtos.requests;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FillterRequest {
    private String status;
    private String name;
}
