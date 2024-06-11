package org.minab.entity.enums;

public enum ESos
{
    HARDAL(3.0),BARBEKU (5.0),ACI_SOS   (2.0),KETCAP    (4.0),MAYONEZ (3.0)  ;


    private final double fiyat;


    ESos(double fiyat)
    {
        this.fiyat = fiyat;
    }

    public double getFiyat()
    {
        return fiyat;
    }
}
