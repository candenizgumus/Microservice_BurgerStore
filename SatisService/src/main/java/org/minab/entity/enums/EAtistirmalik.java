package org.minab.entity.enums;

public enum EAtistirmalik {
    NUGGET(50.0),
    CITIR_PEYNIR(62.0),
    SOGAN_HALKASI(40.0),
    FALAFEL(49.0),
    PATATES_KIZARTMASI(50.0);

    private final double fiyat;

    EAtistirmalik(double fiyat) {
        this.fiyat = fiyat;
    }

    public double getFiyat() {
        return fiyat;
    }
}
