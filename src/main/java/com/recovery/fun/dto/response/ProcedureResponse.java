package com.recovery.fun.dto.response;

import java.math.BigDecimal;

public record ProcedureResponse(
        Long idProcedure,
        String name,
        String description,
        BigDecimal price) {
}
