package com.leoni.packaging.enums;

public enum CableType {
    OV("OV"),
    OV5("OV5"),
    OV51("OV51"),
    OV52("OV52");

    private final String type;

    private CableType(String type) {
        this.type = type;
    }

    public String getRole() {
        return type;
    }
}
