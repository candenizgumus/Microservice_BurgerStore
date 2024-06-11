package org.minab.service;

import org.minab.dto.SiparisSaveRequestDto;
import org.minab.entity.Siparis;
import org.minab.repository.SiparisRepository;
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
