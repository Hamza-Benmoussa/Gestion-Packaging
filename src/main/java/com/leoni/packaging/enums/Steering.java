package com.leoni.packaging.enums;

public enum Steering {
    LL("LL"),
    RL("RL");

    private final String steering;

    private Steering(String steering) {
        this.steering = steering;
    }

    public String getRole() {
        return steering;
    }
}
