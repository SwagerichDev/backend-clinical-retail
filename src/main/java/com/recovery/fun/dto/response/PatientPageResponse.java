package com.recovery.fun.dto.response;

import java.util.List;
import java.util.Map;

public record PatientPageResponse(
        List<PatientResponse> patients,
        Map<String, Object> pagination) {
}
