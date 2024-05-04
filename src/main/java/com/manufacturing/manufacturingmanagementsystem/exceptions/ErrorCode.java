package com.manufacturing.manufacturingmanagementsystem.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

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
    COMIC_CHAPTERS_NOT_FOUND(4005, "Comic's chapters not found", HttpStatus.BAD_REQUEST),
    CHAPTER_IMAGES_NOT_FOUND(4006, "Chapter's images not found", HttpStatus.BAD_REQUEST),;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
