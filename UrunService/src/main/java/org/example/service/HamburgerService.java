package org.example.service;
import lombok.RequiredArgsConstructor;

import org.example.config.model.HamburgerModel;
import org.example.dto.request.HamburgerSaveRequest;
import org.example.entity.Hamburger;
import org.example.repository.HamburgerRepository;

import org.minab.exceptions.ErrorType;
import org.minab.exceptions.UrunServiceException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HamburgerService
{
    private final HamburgerRepository hamburgerRepository;



    @CacheEvict(allEntries = true, value = "hamburger")
    public String save(HamburgerSaveRequest dto)
    {
        hamburgerRepository.save(Hamburger.builder().ad(dto.getAd()).aciklama(dto.getAciklama()).birimFiyat(dto.getBirimFiyat()).build());
        return "Hamburger kaydedildi.";
    }


    @RabbitListener(queues = "findhamburger")
    @Cacheable(value = "hamburger", key = "#hamburgerId")
    public HamburgerModel find(Long hamburgerId)
    {
        //Rabbitlistener ve cache olan yerde hata fırlatınca rabbit sonsuz döngüye giriyor cache de ayrı hatalar fırlatıyor.
        Hamburger hamburger = hamburgerRepository.findById(hamburgerId).orElse(null);
        if (hamburger == null) {

            System.err.println("Hamburger not found with ID: " + hamburgerId);

            return HamburgerModel.builder().id(-1L).ad("Hamburger Not Found").aciklama("Hamburger Not Found").birimFiyat(0.0).build();
        }
        HamburgerModel hamburgerModel = HamburgerModel.builder().id(hamburger.getId()).ad(hamburger.getAd()).aciklama(hamburger.getAciklama()).birimFiyat(hamburger.getBirimFiyat()).build();
        return hamburgerModel;
    }



    @Cacheable(value = "hamburger")
    public List<HamburgerModel> findAll() {
        List<Hamburger> hamburgers = hamburgerRepository.findAll();
        return hamburgers.stream()
                .map(hamburger -> HamburgerModel.builder()
                        .id(hamburger.getId())
                        .ad(hamburger.getAd())
                        .aciklama(hamburger.getAciklama())
                        .birimFiyat(hamburger.getBirimFiyat())
                        .build())
                .collect(Collectors.toList());

    }


}
