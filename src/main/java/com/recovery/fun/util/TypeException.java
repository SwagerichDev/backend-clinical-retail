package com.recovery.fun.util;

import lombok.Getter;

@Getter
public enum TypeException {


    I("Informativo"), A("Advertencia"), E("Error");

    private final String value;

    TypeException(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
