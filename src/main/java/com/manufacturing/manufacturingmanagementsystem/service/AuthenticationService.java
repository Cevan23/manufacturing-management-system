package com.manufacturing.manufacturingmanagementsystem.service;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.IntrospectRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.LoginRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.IntrospectResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.AuthResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final JwtService jwtService;


    public AuthResponse login(LoginRequest loginRequest) throws AppException, Exception {
        AuthResponse loginResponse = null;
        try {
            if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
                throw new IllegalArgumentException("Email or password cannot be null");
            }
            System.out.println("Email: " + loginRequest.getEmail());
            System.out.println("Password: " + loginRequest.getPassword());
            Optional<UsersEntity> UserOptional = usersRepository.findByEmail(loginRequest.getEmail());
            if (UserOptional.isPresent()) {
                UsersEntity User = UserOptional.get();
                if (passwordEncoder.matches(loginRequest.getPassword(), User.getPassword())) {
                    String jwtToken = "";
                    jwtToken = jwtService.generateToken(User);
                    loginResponse = AuthResponse.builder()
                            .id(User.getId())
                            .token(jwtToken)
                            .authenticated(true)
                            .build();
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (loginResponse == null) {
            throw new AppException(ErrorCode.WRONG_EMAIL_OR_PASSWORD);
        }
        return loginResponse;
    }

    //    @PreAuthorize("hasRole('chairman')")
    public IntrospectResponse introspect(IntrospectRequest request) throws AppException {
        var token = request.getToken();

        if (token == null) {
            throw new AppException(ErrorCode.TOKEN_IS_REQUIRED);
        }

        return jwtService.introspect(token);
    }
}
