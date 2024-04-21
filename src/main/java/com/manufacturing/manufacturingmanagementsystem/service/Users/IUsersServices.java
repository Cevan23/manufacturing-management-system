package com.manufacturing.manufacturingmanagementsystem.service.Users;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;

import java.util.List;

public interface IUsersServices {
    List<UsersEntity> getAllUsers();
    UsersEntity findUserbyEmail(String email);
    UsersEntity getUserById(Long id);
    UsersEntity insertUser(UsersDTO userForm);
    UsersEntity updateUser(UsersDTO userForm);
    void deleteUser(Long id);
    UsersEntity findUserbyRole(String roleName);
}

