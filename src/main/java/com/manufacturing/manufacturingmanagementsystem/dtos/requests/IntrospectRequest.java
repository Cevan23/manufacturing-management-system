package com.manufacturing.manufacturingmanagementsystem.dtos.requests;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the Fillter request
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntrospectRequest {
    private String token;
}