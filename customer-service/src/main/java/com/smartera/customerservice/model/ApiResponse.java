package com.smartera.customerservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author yilmazsahin
 * @since 2/27/2024
 */

@Getter
@Setter
public class ApiResponse<T> {
    private HttpStatus status;
    private String errorMessage;
    private T body;

    public static <T> ApiResponse<T> of(HttpStatus status, T body) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(status);
        response.setBody(body);
        return response;
    }

    public static <T> ApiResponse<T> of(HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(status);
        return response;
    }

    public static <T> ApiResponse<T> of(HttpStatus status, String errorMessage) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(status);
        response.setErrorMessage(errorMessage);
        return response;
    }

}
