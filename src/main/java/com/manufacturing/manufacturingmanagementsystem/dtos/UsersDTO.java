package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;

import java.util.Date;
// Author: Pham Van Cao
// this class is used to handle the UsersDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private String email;

    private String password;

    private String fullName;

    private String phoneNumber;

    private Date dateOfBirth;

    private String address;

    private String roleName;
}
