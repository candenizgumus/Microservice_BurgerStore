package org.candenizgumus.service;

import org.candenizgumus.dto.request.AdresSaveRequest;
import org.candenizgumus.entity.Adres;
import org.candenizgumus.entity.UserProfile;
import org.candenizgumus.repository.AdresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdresService
{
    private final AdresRepository adresRepository;
    private final UserProfileService userProfileService;

    public String save(AdresSaveRequest dto)
    {
        UserProfile userProfile = userProfileService.findById(dto.getUserProfileId());

        //Adres oluşturma
        Adres adres = Adres.builder()
                .adres(dto.getAdres())
                .adresType(dto.getAdresType())
                .userProfileId(userProfile.getId())
                .build();

        adresRepository.save(adres);
        return "Adres Olusturma Basarili";
    }
}
