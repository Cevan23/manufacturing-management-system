package com.manufacturing.manufacturingmanagementsystem.dtos.requests;

import lombok.*;
// Author: Pham Hien Nhan
// this class is used to handle the RecoverPassword request
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecoverPasswordRequest {
    private String email;
}