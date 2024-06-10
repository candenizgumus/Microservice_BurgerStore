package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Hamburger;
import org.example.entity.enums.ECikartilacakUrunMalzemeleri;
import org.example.entity.enums.EExtraMalzeme;
import org.example.entity.enums.EPismeDerecesi;
import org.example.entity.enums.ESos;
import org.example.repository.HamburgerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class HamburgerService
{
    private final HamburgerRepository  hamburgerRepository;

    public String save(Set<ECikartilacakUrunMalzemeleri> malzemeler, Set<EExtraMalzeme> ekstraMalzemeler, EPismeDerecesi pismeDerecesi, Set<ESos> soslar , Double hamburgerBirimFiyati, String ad)
    {
        Hamburger hamburger = new Hamburger();
        hamburger.setAd(ad);
        hamburger.setCikartilacakMalzemeler(malzemeler);
        hamburger.setEkstraMalzemeler(ekstraMalzemeler);
        hamburger.setPismeDerecesi(pismeDerecesi);
        hamburger.setSoslar(soslar);
        hamburger.setHamburgerBirimFiyati(hamburgerBirimFiyati);
        hamburger.setToplamFiyat();
        hamburgerRepository.save(hamburger);


        return "Hamburger Kaydedildi.";
    }
}
