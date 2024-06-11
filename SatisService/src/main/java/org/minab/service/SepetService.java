package org.minab.service;

import org.example.config.model.HamburgerModel;
import org.minab.entity.Sepet;
import org.minab.entity.SepetDetay;
import org.minab.entity.enums.ECikartilacakUrunMalzemeleri;
import org.minab.entity.enums.EExtraMalzeme;
import org.minab.entity.enums.EPismeDerecesi;
import org.minab.entity.enums.ESos;
import org.minab.exceptions.ErrorType;
import org.minab.exceptions.SatisServiceException;
import org.minab.repository.SepetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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

    public Sepet sepetiGoruntule(Long sepetId)
    {
        Sepet sepet = sepetRepository.findById(sepetId).orElseThrow(() -> new SatisServiceException(ErrorType.SEPET_NOT_FOUND));
        List<SepetDetay> sepetDetayList = sepetDetayService.findAllBySepetId(sepetId);
        sepet.setAraToplam(0.0); //TODO HATADAN KAYNAKLI BURADA ARA TOPLAM SIFIRA EŞİTLENDİ
        sepetDetayList.forEach(sepetDetay -> sepet.getSepetDetayList().add(sepetDetay));
        sepetDetayList.forEach(sepetDetay -> sepet.setAraToplam(sepetDetay.getToplamFiyat()+sepet.getAraToplam()));
        sepet.setVergi(sepet.getAraToplam()*0.2);
        sepet.setToplam(sepet.getAraToplam()+sepet.getVergi());
        return sepet;

    }

    public String sepetiTemizle(Long sepetId)
    {
        List<SepetDetay> sepetDetayList = sepetDetayService.findAllBySepetId(sepetId);
        if (sepetDetayList.isEmpty())
        {
            throw new SatisServiceException(ErrorType.SEPET_EMPTY);
        }
        sepetDetayList.forEach(sepetDetay -> sepetDetayService.deleteById(sepetDetay.getId()));
        return "Sepet temizlendi";
    }
}
