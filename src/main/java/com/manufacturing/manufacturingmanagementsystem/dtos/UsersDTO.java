package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private Long roleId;

    private String email;

    private String password;

    private String fullName;

    private String phoneNumber;

    private Date dateOfBirth;

    private String address;
}
