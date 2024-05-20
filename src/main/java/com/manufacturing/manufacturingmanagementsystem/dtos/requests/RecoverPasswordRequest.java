package com.manufacturing.manufacturingmanagementsystem.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecoverPasswordRequest {
    private String email;
}