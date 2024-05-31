package com.manufacturing.manufacturingmanagementsystem.dtos.requests;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the Login request
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    private String email;

    private String password;
}