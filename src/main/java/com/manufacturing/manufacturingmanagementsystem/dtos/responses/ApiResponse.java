package com.manufacturing.manufacturingmanagementsystem.dtos.responses;


import lombok.*;

// Author: Pham Van Cao
// this class is used to handle the ApiResponse response
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApiResponse <T> {
    @Builder.Default
    private int code = 1000;
    private String message;
    private T result;
}