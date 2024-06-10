package com.minab.service;

import com.minab.entity.Sepet;
import com.minab.entity.enums.ECikartilacakUrunMalzemeleri;
import com.minab.entity.enums.EExtraMalzeme;
import com.minab.entity.enums.EPismeDerecesi;
import com.minab.entity.enums.ESos;
import com.minab.repository.SepetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class SepetService {
    private final SepetRepository sepetRepository;


    @RabbitListener(queues = "savesepet")
    public Long save(Long userProfileId) {


        Sepet sepet = sepetRepository.save(Sepet.builder().userProfileId(userProfileId).build());

        return sepet.getId();

    }





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
