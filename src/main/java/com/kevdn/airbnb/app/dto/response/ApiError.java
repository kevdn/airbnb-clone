package com.kevdn.airbnb.app.dto.response;

public interface ApiError {

    String message();

    static ApiError createFieldApiError(String field, String message, Object rejectedValue) {
        return new ApiErrorField(field, message, rejectedValue);
    }

    record ApiErrorField(String field, String message, Object rejectedValue) implements ApiError {
    }
}
