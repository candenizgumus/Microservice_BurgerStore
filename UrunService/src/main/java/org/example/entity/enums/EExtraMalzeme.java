package org.example.entity.enums;


public enum EExtraMalzeme
{
    KARAMELIZE_SOGAN(5.0),
    CHEDDAR_PEYNIR(11.0),
    DANA_FUME(17.0),
    MANTAR(9.0),
    JALAPENO(5.0),
    BUYUK_PATATES_FARKI(22.0),
    TURSU(4.0),
    KOZLENMIS_BIBER(7.0),
    SOGAN(5.0);

    private final double fiyat;

    EExtraMalzeme(double fiyat)
    {
        this.fiyat = fiyat;
    }

    public double getFiyat()
    {
        return fiyat;
    }
}
