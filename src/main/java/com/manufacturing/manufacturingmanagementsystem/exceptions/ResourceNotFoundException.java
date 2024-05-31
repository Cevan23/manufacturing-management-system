package com.manufacturing.manufacturingmanagementsystem.exceptions;
// Author: Pham Van Cao
// this class is used to handle the ResourceNotFoundException response
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
