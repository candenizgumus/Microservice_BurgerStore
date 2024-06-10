package com.candenizgumus.service;

import com.candenizgumus.dto.request.AuthRegisterRequest;
import com.candenizgumus.entity.Auth;
import com.candenizgumus.entity.UserProfile;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.exceptions.KullaniciServiceException;
import com.candenizgumus.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService
{
    private final AuthRepository authRepository;
    private final UserProfileService userProfileService;

    public String save(AuthRegisterRequest dto)
    {
        if (!dto.getSifre().equals(dto.getSifreTekrar()))
        {
            throw new KullaniciServiceException(ErrorType.SIFRELER_AYNI_DEGIL);
        }
        if (authRepository.findByEmail(dto.getEmail()).isPresent())
        {
            throw new KullaniciServiceException(ErrorType.EMAIL_TAKEN);
        }
        if (!dto.getSozlesmeOnayMetni())
        {
            throw new KullaniciServiceException(ErrorType.SORUMLULUK_SOZLESME_ONAYI);
        }

        //Auth Nesnesi Oluşturma
        Auth auth = Auth.builder()
                .telefon(dto.getTelefon())
                .email(dto.getEmail())
                .sifre(dto.getSifre())
                .ad(dto.getAd())
                .soyad(dto.getSoyad())
                .cinsiyet(dto.getCinsiyet())
                .dogumTarihi(dto.getDogumTarihi())
                .build();

        authRepository.save(auth);

        //UserProfile nesnesi olusturma
        UserProfile userProfile = UserProfile.builder()
                .ad(dto.getAd())
                .soyad(dto.getSoyad())
                .cinsiyet(dto.getCinsiyet())
                .telefon(dto.getTelefon())
                .authId(auth.getId())
                .dogumTarihi(dto.getDogumTarihi())
                .email(dto.getEmail())
                .build();

        userProfileService.save(userProfile);

        return "Kayıt Tamamlandı";


    }
}
