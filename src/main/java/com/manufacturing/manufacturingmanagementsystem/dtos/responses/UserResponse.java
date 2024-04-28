package com.manufacturing.manufacturingmanagementsystem.dtos.responses;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private RoleResponse role;

    private String email;

    private String password;

    private String fullName;

    private String phoneNumber;

    private Date dateOfBirth;

    private String address;
}
