package com.manufacturing.manufacturingmanagementsystem.dtos.responses;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private  Long userid;

    private Long roleId;

    private String email;

    private String password;

    private String fullName;

    private String phoneNumber;

    private Date dateOfBirth;

    private String address;
}
