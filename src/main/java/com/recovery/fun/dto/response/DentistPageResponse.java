package com.recovery.fun.dto.response;

import java.util.List;
import java.util.Map;

public record DentistPageResponse(List<DentistResponse> dentists, Map<String, Object> pagination) {
}
