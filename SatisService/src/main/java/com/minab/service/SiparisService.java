package com.minab.service;

import com.minab.dto.SiparisSaveRequestDto;
import com.minab.entity.Siparis;
import com.minab.repository.SiparisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SiparisService {
    private final SiparisRepository siparisRepository;

    public String save(SiparisSaveRequestDto dto) {
        Siparis siparis = Siparis.builder()
                .sepetId(dto.getSepetId())
                .userProfileId(dto.getUserProfileId())
                .toplamTutar(dto.getToplamTutar())
                .build();
        siparisRepository.save(siparis);
        return "Siparişiniz alınmıştır.";
    }
}
