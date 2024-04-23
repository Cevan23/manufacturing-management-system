package com.manufacturing.manufacturingmanagementsystem.controllers;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.IntrospectRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.LoginRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.AuthResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.IntrospectResponse;
import com.manufacturing.manufacturingmanagementsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        AuthResponse loginResponse = authenticationService.login(loginRequest);

        return ResponseEntity.ok().body(ApiResponse.builder()
                .message("Login successfully")
                .result(loginResponse)
                .build());
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
