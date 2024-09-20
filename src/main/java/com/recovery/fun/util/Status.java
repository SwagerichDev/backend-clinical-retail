package com.recovery.fun.util;

import lombok.Getter;

@Getter
public enum Status {
//    PENDING,
//    CONFIRMED,
    CANCELED("CANCELED");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
