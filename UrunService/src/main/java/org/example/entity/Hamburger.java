package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enums.ECikartilacakUrunMalzemeleri;
import org.example.entity.enums.EExtraMalzeme;
import org.example.entity.enums.EPismeDerecesi;
import org.example.entity.enums.ESos;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblhamburger")
public class Hamburger
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String ad;
    @ElementCollection(targetClass = ECikartilacakUrunMalzemeleri.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ECikartilacakUrunMalzemeleri> cikartilacakMalzemeler;

    @ElementCollection(targetClass = EExtraMalzeme.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<EExtraMalzeme> ekstraMalzemeler;

    @Enumerated(EnumType.STRING)
    private EPismeDerecesi pismeDerecesi;

    @ElementCollection(targetClass = ESos.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ESos> soslar;

    private Double hamburgerBirimFiyati;
    private Double toplamFiyat;


    //TODO HER KULLANICIYA SEPET VER, SEPETDETAYINDA BAĞLANTI KURMA.
    @PrePersist
    public void setToplamFiyat()
    {
        double toplam = 0.0;
        toplam += hamburgerBirimFiyati;

        for (EExtraMalzeme malzeme : ekstraMalzemeler)
        {
            toplam += malzeme.getFiyat();
        }
        for (ESos sos : soslar)
        {
            //2 den fazla sos varsa fiyatını topluyor
            if (soslar.size()>2)
            {
                toplam += sos.getFiyat();
            }

        }
        toplamFiyat = toplam;
    }
}
