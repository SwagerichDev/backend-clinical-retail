package com.recovery.fun.dto.response;


import java.util.List;
import java.util.Map;

public record ProcedurePageResponse(
        List<ProcedureResponse> procedures,
        Map<String, Object> pagination) {
}
