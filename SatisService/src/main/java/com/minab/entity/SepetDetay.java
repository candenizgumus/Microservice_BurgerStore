package com.minab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblsepetdetay")
public class SepetDetay
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long sepetId;
    String urunAdi;
    Double urunFiyati;
    Integer adet;
    Double toplamFiyat;
    @ElementCollection
    List<String> aciklamalar;


    @PrePersist
    public void setToplamFiyat()
    {
        toplamFiyat = urunFiyati * adet;
    }
}
