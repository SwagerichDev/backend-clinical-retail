package com.recovery.fun.util;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {

    private Boolean error;

    private Integer statusCode;

    private String title;

    private String message;

    private TypeException type;

    private LocalDateTime date = LocalDateTime.now();

    private Object data;

    public ApiResponse() {
    }

    public static ApiResponse ok(String mensaje, Object data) {
        return ok("", mensaje, data);
    }

    public static ApiResponse ok(String titulo, String mensaje, Object data) {
        return load(false, titulo, mensaje, 200, data, null);
    }

    public static ApiResponse create(String mensaje, Object data) {
        return create("", mensaje, data);
    }

    public static ApiResponse create(String titulo, String mensaje, Object data) {
        return load(false, titulo, mensaje, 201, data, null);
    }

    public static ApiResponse error(String mensaje, Integer codigo, TypeException type) {
        return ApiResponse.error("", mensaje, codigo, type);
    }

    public static ApiResponse error(String titulo, String mensaje, Integer codigo, TypeException type) {
        return load(true, titulo, mensaje, codigo, null, type);
    }

    public static ApiResponse load(boolean isError, String titulo, String mensaje, Integer codigo, Object data,
                                     TypeException type) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(isError);
        apiResponse.setStatusCode(codigo);
        apiResponse.setTitle(titulo);
        apiResponse.setMessage(mensaje);
        apiResponse.setData(data);
        apiResponse.setType(type);
        return apiResponse;
    }

    public static ApiResponse error(String mensaje) {
        return ApiResponse.error(mensaje, 500, TypeException.E);
    }
}
