package com.manufacturing.manufacturingmanagementsystem.service;

import com.manufacturing.manufacturingmanagementsystem.dtos.responses.IntrospectResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.InvalidatedTokenRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Roles.RolesServices;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.StringJoiner;
import java.util.Date;
import java.util.UUID;

// Author: Pham Van Cao
// this class is used to implement the methods defined in the IJwtService interface
@Service
@AllArgsConstructor
public class JwtService {

    UsersRepository usersRepository;
    private final String SIGNER_KEY = "Cf3X07omDRzLIp2hYuvrBmZ5vGlIcge12VEllyTdD1Q";
    private final InvalidatedTokenRepository invalidatedTokenRepository;
    private final RolesServices rolesServices;

    // this method is used to generate a token
    public String generateToken(UsersEntity User) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        System.out.println("User Id: " + User.getId());
        System.out.println("Role: " + User.getRole().getRoleName());
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(User.getEmail())
                .issueTime(new Date())
                .claim("role",User.getRole().getRoleName())
                .claim("userId", User.getId())
                .claim("scope", buildScope(User))
                .expirationTime(new Date(new Date().getTime() + 30 * 60 * 1000))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    // this method is used to introspect a token
    public IntrospectResponse introspect(String token) throws AppException {
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (Exception e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    // this method is used to verify a token
    public SignedJWT verifyToken(String token) throws Exception {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expirationTime.after(new Date())))
            throw new Exception();

        String jit = signedJWT.getJWTClaimsSet().getJWTID();

        if (invalidatedTokenRepository.existsById(jit))
            throw new Exception();

        return signedJWT;
    }

    // this method is used to invalidate a token
    private String buildScope(UsersEntity user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (user != null && user.getRole() != null) {
            // Thêm tên vai trò của người dùng vào scope
            stringJoiner.add("SCOPE_" + user.getRole().getRoleName());

            // Lấy danh sách quyền từ vai trò của người dùng và thêm vào scope
            if (user.getRole().getPermissions() != null && !user.getRole().getPermissions().isEmpty()) {
                user.getRole().getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            }
        }

        return stringJoiner.toString();
    }

}
