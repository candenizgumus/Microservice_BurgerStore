package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.HamburgerSaveRequest;
import org.example.entity.Hamburger;
import org.example.exceptions.ErrorType;
import org.example.exceptions.UrunServiceException;
import org.example.repository.HamburgerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class HamburgerService
{
    private final HamburgerRepository  hamburgerRepository;


    public String save(HamburgerSaveRequest dto)
    {
        hamburgerRepository.save(Hamburger.builder().ad(dto.getAd()).aciklama(dto.getAciklama()).birimFiyat(dto.getBirimFiyat()).build());
        return "Hamburger kaydedildi.";
    }

    @RabbitListener("findhamburger")
    public Hamburger find(Long id)
    {
        return hamburgerRepository.findById(id).orElseThrow(() -> new UrunServiceException(ErrorType.HAMBURGER_NOT_FOUND));
    }
}
