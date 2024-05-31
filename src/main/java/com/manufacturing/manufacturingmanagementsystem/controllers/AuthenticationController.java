package com.manufacturing.manufacturingmanagementsystem.controllers;
import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.IntrospectRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.LoginRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RecoverPasswordRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.*;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.service.AuthenticationService;
import com.manufacturing.manufacturingmanagementsystem.service.Users.UsersServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
// Author: Pham Van Cao
// this class is used to handle the authentication
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
    private final UsersServices userService;
    @Autowired
    private AuthenticationService authenticationService;
    // Author: Pham Van Cao
    // this method is used to handle the login request
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            AuthResponse loginResponse = authenticationService.login(loginRequest);

            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Login successfully")
                    .result(loginResponse)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    // Author: Pham Van Cao
    // this method is used to handle the introspect request
    @PostMapping("/introspect")
    public ResponseEntity<ApiResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws Exception {
        IntrospectResponse introspectResponse = null;

        introspectResponse = authenticationService.introspect(introspectRequest);

        return ResponseEntity.ok().body(ApiResponse.builder()
                .message("Introspect successfully")
                .result(introspectResponse)
                .build());
    }
    // Author: Pham Van Cao
    // this method is used to handle the recover password request
    @PutMapping("/recover-password")
    public ResponseEntity<?> resetPassword(@RequestBody RecoverPasswordRequest recoverPasswordRequest) {
        try {
            UsersEntity user = authenticationService.recoverPassword(recoverPasswordRequest);

            return ResponseEntity.ok(ResponseObject.builder()
                    .data(user)
                    .message("Recover password successfully")
                    .status(HttpStatus.OK)
                    .build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Author: Pham Van Cao
    // this method is used to handle the create user request
    @PostMapping("/create")
    public ResponseEntity<?> insertUser(@Valid @RequestBody UsersDTO userDto) {
        try {
            Map<String, Object> newUser = userService.insertUser(userDto);
            return ResponseEntity.ok(ResponseObject.builder()
                                 .data(newUser)
                                 .message("Add user successfully")
                                 .status(HttpStatus.OK)
                                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
