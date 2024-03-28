package com.manufacturing.manufacturingmanagementsystem.model;
import com.manufacturing.manufacturingmanagementsystem.model.audit.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class UserEntity extends Auditable<String> {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role", columnDefinition = "varchar(255) check (role in ('Student', 'University', 'CommunityLeader', 'Administrator'))")
    private String role;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;

}
