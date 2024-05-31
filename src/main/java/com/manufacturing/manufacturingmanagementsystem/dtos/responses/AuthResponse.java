package com.manufacturing.manufacturingmanagementsystem.dtos.responses;
import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the AuthResponse response
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthResponse {
    private Long id;

    private String token;

    private boolean authenticated;
}
