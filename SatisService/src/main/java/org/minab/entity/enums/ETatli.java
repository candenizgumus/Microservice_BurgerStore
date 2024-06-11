package org.minab.entity.enums;

public enum ETatli {
    MAGNOLIA(55.0),
    CHURROS(48.0);


    private final double fiyat;

    ETatli(double fiyat) {
        this.fiyat = fiyat;
    }

    public double getFiyat() {
        return fiyat;
    }
}
