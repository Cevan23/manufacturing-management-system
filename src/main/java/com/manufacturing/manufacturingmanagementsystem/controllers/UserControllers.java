package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
import com.manufacturing.manufacturingmanagementsystem.mapper.RoleMapper;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.service.Users.UsersServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllers {


    private final UsersServices userService;

    RoleMapper roleMapper;

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

    @PostMapping("/create")
    public UserResponse insertUser(@RequestBody UsersDTO userDto) {
        try {
            // Log thông tin đầu vào
            System.out.println("Received user DTO: " + userDto.toString());
            UsersEntity newUser = userService.insertUser(userDto);
            RoleResponse role = roleMapper.toRoleResponse(newUser.getRole());
            // Tạo một đối tượng UserResponse từ thông tin của người dùng mới
            UserResponse userResponse = UserResponse.builder()
                    .role(role)
                    .email(newUser.getEmail())
                    .password(newUser.getPassword())
                    .build();

            // Trả về phản hồi thành công (status code 200) kèm theo đối tượng UserResponse
            System.out.println("Received user response: " + userResponse.toString());
            return userResponse;
        } catch (Exception e) {
            // Xử lý lỗi và trả về phản hồi lỗi (status code 500)
            String errorMessage = "Failed to create new user: " + e.getMessage();
            return null;
        }
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
