package com.candenizgumus.service;

import com.candenizgumus.dto.request.AdresSaveRequest;
import com.candenizgumus.entity.Adres;
import com.candenizgumus.entity.UserProfile;
import com.candenizgumus.repository.AdresRepository;
import com.candenizgumus.repository.AuthRepository;
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
