package org.minab.service;

import org.minab.entity.SepetDetay;
import org.minab.repository.SepetDetayRepository;
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
