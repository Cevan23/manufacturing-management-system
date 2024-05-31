package com.manufacturing.manufacturingmanagementsystem.service.Users;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;

import java.util.List;
import java.util.Map;
// Author: Pham Hien Nhan
// this interface is used to declare the methods that will be implemented in the UsersServices class
public interface IUsersServices {
    List<UsersEntity> getAllUsers();
    UsersEntity findUserbyEmail(String email);
    UsersEntity getUserById(Long id);
    Map<String, Object> insertUser(UsersDTO userForm);
    Map<String, Object> updateUser(long id,UsersDTO userForm);
    UsersEntity resetPassword(long id, UsersDTO userDto);
    void deleteUser(Long id);
    UsersEntity findUserbyRole(String roleName);
    List<UsersEntity> findAllSignUpRequest(long id);
    UsersEntity updateRoleId(String email, UsersDTO usersDto);
    List<UsersEntity> findAllEmployee(long id);
}

