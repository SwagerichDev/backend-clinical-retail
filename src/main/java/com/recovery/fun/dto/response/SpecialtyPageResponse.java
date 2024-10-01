package com.recovery.fun.dto.response;

import java.util.List;
import java.util.Map;

public record SpecialtyPageResponse(List<SpecialtyResponse> specialties, Map<String, Object> pagination) {
}
