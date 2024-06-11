package org.minab.service;

import org.minab.entity.SepetDetay;
import org.minab.exceptions.ErrorType;
import org.minab.exceptions.SatisServiceException;
import org.minab.repository.SepetDetayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<SepetDetay> findAllBySepetId(Long sepetId)
    {
        return sepetDetayRepository.findAllBySepetId(sepetId);
    }
}
