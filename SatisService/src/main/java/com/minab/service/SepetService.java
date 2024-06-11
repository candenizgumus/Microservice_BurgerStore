package com.minab.service;

import com.minab.config.model.HamburgerModel;
import com.minab.entity.Sepet;
import com.minab.entity.SepetDetay;
import com.minab.entity.enums.ECikartilacakUrunMalzemeleri;
import com.minab.entity.enums.EExtraMalzeme;
import com.minab.entity.enums.EPismeDerecesi;
import com.minab.entity.enums.ESos;
import com.minab.exceptions.ErrorType;
import com.minab.exceptions.SatisServiceException;
import com.minab.repository.SepetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class SepetService {
    private final SepetRepository sepetRepository;
    private final RabbitTemplate rabbitTemplate;
    private final SepetDetayService sepetDetayService;


    @RabbitListener(queues = "savesepet")
    public Long save(Long userProfileId) {


        Sepet sepet = sepetRepository.save(Sepet.builder().userProfileId(userProfileId).build());

        return sepet.getId();

    }


    public String sepeteHamburgerEkle(Long sepetId,Long hamburgerId, Integer adet, Set<ECikartilacakUrunMalzemeleri> cikarilacakMalzemeler, Set<EExtraMalzeme> ekstraMalzemeler, EPismeDerecesi pismeDerecesi, Set<ESos> soslar)
    {
        //Hamburgeri bulma
        HamburgerModel hamburgerModel = (HamburgerModel)rabbitTemplate.convertSendAndReceive("direct.exchange", "key.findhamburger", hamburgerId);
        //Sepeti bulma ve kontrol
        Sepet sepet = sepetRepository.findById(sepetId).orElseThrow(() -> new SatisServiceException(ErrorType.SEPET_NOT_FOUND));
        //Fiyatını hesaplama
        Double fiyatFarki = sosFiyatFarkiHesaplama(ekstraMalzemeler, soslar);


        //Aciklamalari olusturma
        List<String> aciklamalar = new ArrayList<>();

        cikarilacakMalzemeler.forEach(malzeme -> aciklamalar.add(malzeme.name()));
        ekstraMalzemeler.forEach(malzeme -> aciklamalar.add(malzeme.name()));
        soslar.forEach(sos -> aciklamalar.add(sos.name()));
        aciklamalar.add(pismeDerecesi.name());

        //SepetDetay nesnesi oluşturma


        SepetDetay sepetDetay = SepetDetay.builder()
                .aciklamalar(aciklamalar)
                .urunAdi(hamburgerModel.getAd())
                .urunFiyati(hamburgerModel.getBirimFiyat() + fiyatFarki)
                .adet(adet)
                .sepetId(sepet.getId())
                .build();

        sepetDetayService.save(sepetDetay);
        return "Hamburger sepete eklendi";

    }

    private Double sosFiyatFarkiHesaplama(Set<EExtraMalzeme> ekstraMalzemeler, Set<ESos> soslar)
    {
        double ekstraFiyatFarki = 0.0;


        for (EExtraMalzeme malzeme : ekstraMalzemeler)
        {
            ekstraFiyatFarki += malzeme.getFiyat();
        }
        for (ESos sos : soslar)
        {
            //2 den fazla sos varsa fiyatını topluyor
            if (soslar.size()>2)
            {
                ekstraFiyatFarki += sos.getFiyat();
            }

        }

        return ekstraFiyatFarki;
    }
}
