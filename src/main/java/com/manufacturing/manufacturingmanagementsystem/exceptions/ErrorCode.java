package com.manufacturing.manufacturingmanagementsystem.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
// Author: Pham Van Cao
// this class is used to handle the ErrorCode response
@Getter
public enum ErrorCode {
    ACCESS_DENIED(8888, "Access denied, you don't have permission", HttpStatus.OK),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    WRONG_EMAIL_OR_PASSWORD(4000, "Wrong email or password", HttpStatus.BAD_REQUEST),
    TOKEN_IS_REQUIRED(4001, "Token is required", HttpStatus.BAD_REQUEST),
    TOKEN_INVALID(4002, "Token is invalid", HttpStatus.BAD_REQUEST),
    EMAIL_TAKEN(4003, "Email is already taken", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(4004, "User not found", HttpStatus.BAD_REQUEST),
    BOM_NOT_FOUND(4007, "BOM not found", HttpStatus.BAD_REQUEST),
    STATUS_INCORRECT(4008, "Status is incorrect", HttpStatus.BAD_REQUEST),
    BAD_REQUEST(4009, "Bad request", HttpStatus.BAD_REQUEST),
    NOT_FOUND(5000,"not found",HttpStatus.BAD_REQUEST),
    DATA_ALREADY_EXISTS(5001,"Data already exists",HttpStatus.BAD_REQUEST),;



    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
