package com.manufacturing.manufacturingmanagementsystem.dtos.requests;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the Fillter request
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
