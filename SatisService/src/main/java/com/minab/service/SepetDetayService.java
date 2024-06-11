package com.minab.service;

import com.minab.entity.SepetDetay;
import com.minab.repository.SepetDetayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SepetDetayService
{
    private final SepetDetayRepository sepetDetayRepository;

    public String save(SepetDetay sepetDetay)
    {
        sepetDetayRepository.save(sepetDetay);
        return "Sepet detay olusturuldu";
    }
}
