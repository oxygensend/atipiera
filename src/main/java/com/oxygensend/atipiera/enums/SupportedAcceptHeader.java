package com.oxygensend.atipiera.enums;

public enum SupportedAcceptHeader {
    APPLICATION_JSON("application/json"),
    APPLICATION_ALL("*/*");

    private final String value;

    SupportedAcceptHeader(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean isHeaderSupported(String value) {
        for (SupportedAcceptHeader header : SupportedAcceptHeader.values()) {
            if (header.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
