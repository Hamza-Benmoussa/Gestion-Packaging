package com.leoni.packaging.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    private Role(String role) {
        this.role = role;
    }

    public String getSize() {
        return role;
    }
}
