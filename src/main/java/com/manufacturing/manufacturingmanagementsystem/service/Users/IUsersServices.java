package com.manufacturing.manufacturingmanagementsystem.service.Users;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;

import java.util.List;
import java.util.Map;

public interface IUsersServices {
    List<UsersEntity> getAllUsers();
    UsersEntity findUserbyEmail(String email);
    UsersEntity getUserById(Long id);
    Map<String, Object> insertUser(UsersDTO userForm);
    Map<String, Object> updateUser(long id,UsersDTO userForm);
    void deleteUser(Long id);
    UsersEntity findUserbyRole(String roleName);
}

