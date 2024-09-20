package com.recovery.fun.dto.response;

import com.recovery.fun.proyection.QuoteProyectionDto;

import java.util.List;
import java.util.Map;


public record QuotePageResponse(List<QuoteProyectionDto> quotes, Map<String, Object> pagination) {
}
