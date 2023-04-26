package com.leoni.packaging.enums;

public enum UserStatus {
    initial("initial"),
    deactive("deactive"),
    active("active");

    private final String user;

    private UserStatus(String user) {
        this.user = user;
    }

    public String getSize() {
        return user;
    }
}

