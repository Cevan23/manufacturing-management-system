package com.manufacturing.manufacturingmanagementsystem.controllers;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.IntrospectRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.LoginRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.*;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

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

    @PostMapping("/introspect")
    public ResponseEntity<ApiResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws Exception {
        IntrospectResponse introspectResponse = null;

        introspectResponse = authenticationService.introspect(introspectRequest);

        return ResponseEntity.ok().body(ApiResponse.builder()
                .message("Introspect successfully")
                .result(introspectResponse)
                .build());
    }
}
