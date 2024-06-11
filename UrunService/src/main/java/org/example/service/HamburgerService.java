package org.example.service;
import lombok.RequiredArgsConstructor;

import org.example.config.model.HamburgerModel;
import org.example.dto.request.HamburgerSaveRequest;
import org.example.entity.Hamburger;
import org.example.repository.HamburgerRepository;

import org.minab.exceptions.ErrorType;
import org.minab.exceptions.UrunServiceException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HamburgerService
{
    private final HamburgerRepository hamburgerRepository;


    public String save(HamburgerSaveRequest dto)
    {
        hamburgerRepository.save(Hamburger.builder().ad(dto.getAd()).aciklama(dto.getAciklama()).birimFiyat(dto.getBirimFiyat()).build());
        return "Hamburger kaydedildi.";
    }

    @RabbitListener(queues = "findhamburger")
    public HamburgerModel find(Long hamburgerId)
    {
        Hamburger hamburger = hamburgerRepository.findById(hamburgerId).orElseThrow(() -> new UrunServiceException(ErrorType.HAMBURGER_NOT_FOUND));
        HamburgerModel hamburgerModel = HamburgerModel.builder().id(hamburger.getId()).ad(hamburger.getAd()).aciklama(hamburger.getAciklama()).birimFiyat(hamburger.getBirimFiyat()).build();
        return hamburgerModel;
    }
}
