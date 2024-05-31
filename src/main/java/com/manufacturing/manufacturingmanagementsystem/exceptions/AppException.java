package com.manufacturing.manufacturingmanagementsystem.exceptions;

import lombok.Getter;
import lombok.Setter;
// Author: Pham Van Cao
// this class is used to handle the AppException response
@Getter
@Setter
public class AppException extends RuntimeException {

    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}