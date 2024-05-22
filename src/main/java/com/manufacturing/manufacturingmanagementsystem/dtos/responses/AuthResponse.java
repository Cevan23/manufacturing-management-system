package com.manufacturing.manufacturingmanagementsystem.dtos.responses;
import lombok.*;

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
