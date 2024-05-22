package com.manufacturing.manufacturingmanagementsystem.service;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.IntrospectRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.LoginRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RecoverPasswordRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.IntrospectResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final JwtService jwtService;

    @Autowired
    private JavaMailSender javaMailSender;

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

    public UsersEntity recoverPassword(RecoverPasswordRequest recoverPasswordRequest) {
        try {
            Optional<UsersEntity> userEntityOptional = usersRepository.findByEmail(recoverPasswordRequest.getEmail());
            if (userEntityOptional.isPresent()) {
                UsersEntity userEntity = userEntityOptional.get();
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

                // Generate a random password
                String randomPassword = generateRandomPassword(12);

                // Encode the random password
                String encodedPassword = passwordEncoder.encode(randomPassword);
                userEntity.setPassword(encodedPassword);
                usersRepository.save(userEntity);
                sendEmail(recoverPasswordRequest.getEmail(), randomPassword);

                return userEntity;
            } else {
                throw new RuntimeException("User not found with email: " + recoverPasswordRequest.getEmail());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }

    private String generateRandomPassword(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    private void sendEmail(String to, String newPassword) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Recover your password");
            message.setText("You have forgotten your password? Don't worry. Here is your recovery password: " + newPassword + "\n\n" +
                    "You can use this password to access the services at Manufactorio." + "\n\n" +
                    "If you have any questions about our production or services, please feel free to contact us at any time.\n\n" +
                    "Sincerely,\nManufactorio");
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}
