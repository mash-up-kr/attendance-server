package kr.mashup.attendance.controller;

import java.util.List;

import kr.mashup.attendance.domain.ResultCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T> {
    private final ResultCode code;
    private final String message;
    private final T data;

    private ApiResponse(ResultCode code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(
            ResultCode.SUCCESS,
            ResultCode.SUCCESS.getMessage(),
            null
        );
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
            ResultCode.SUCCESS,
            ResultCode.SUCCESS.getMessage(),
            data
        );
    }

    public static <T> ApiResponse<List<T>> success(List<T> data) {
        return new ApiResponse<>(
            ResultCode.SUCCESS,
            ResultCode.SUCCESS.getMessage(),
            data
        );
    }

    public static <T> ApiResponse<T> failure(ResultCode resultCode) {
        return new ApiResponse<T>(
            resultCode,
            resultCode.getMessage(),
            null
        );
    }

    public static <T> ApiResponse<T> failure(ResultCode resultCode, String message) {
        return new ApiResponse<>(
            resultCode,
            message,
            null
        );
    }
}
