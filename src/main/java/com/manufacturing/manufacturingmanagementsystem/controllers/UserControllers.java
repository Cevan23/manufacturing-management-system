package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.service.Users.UsersServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllers {

    private final UsersServices userService;

    @GetMapping
    public List<UsersEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserInformationById")
    public UsersEntity getUserById(Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getUserInformationByEmail")
    public UsersEntity getUserInformationByEmail(String email) {
        return userService.findUserbyEmail(email);
    }

    @GetMapping("/getUserInformationByRole")
    public UsersEntity getUserInformationByRole(String roleName) {
        return userService.findUserbyRole(roleName);
    }

    @PostMapping
    public UsersEntity insertUser(@RequestBody UsersDTO userDto) {
        return userService.insertUser(userDto);
    }

    @PutMapping("/{id}")
    public UsersEntity updateUser(@PathVariable Long id, @RequestBody UsersDTO userDto) {

        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/getMyInfo")
    public ResponseEntity<ApiResponse> getMyInfo() {
        UserResponse userInformation = userService.getMyInfor();
        return ResponseEntity.ok().body(
                ApiResponse
                        .builder()
                        .message("Get user information successfully")
                        .result(userInformation)
                        .build());
    }

}
