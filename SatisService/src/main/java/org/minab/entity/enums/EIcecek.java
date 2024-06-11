package org.minab.entity.enums;

public enum EIcecek {
    COCA_COLA(44.0),
    SPRITE(44.0),
    FANTA(44.0),
    SODA(44.0),
    FUSE_TEA(44.0),
    AYRAN(39.0),
    SU(16.0);

    private final double fiyat;

    EIcecek(double fiyat) {
        this.fiyat = fiyat;
    }

    public double getFiyat() {
        return fiyat;
    }
}
