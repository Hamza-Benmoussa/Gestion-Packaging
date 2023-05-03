package com.leoni.packaging.enums;

public enum ScanKey {
    FOURNISSEUR("FOURNISSEUR"),
    ETICKET("ETICKET"),
    QUANTITE("QUANTITE"),
    CABLE("CABLE");

    private final String scanKey;

    private ScanKey(String scanKey) {
        this.scanKey = scanKey;
    }

    public String getScanKey() {
        return scanKey;
    }
}
