package com.recovery.fun.dto.response;


import java.time.LocalDateTime;

public record QuoteResponse(Long qouteId, LocalDateTime datee, boolean status) {
}
