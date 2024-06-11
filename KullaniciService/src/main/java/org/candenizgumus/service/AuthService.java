package com.candenizgumus.service;

import com.candenizgumus.dto.request.ActivateCodeRequestDto;
import com.candenizgumus.dto.request.AuthRegisterRequest;
import com.candenizgumus.entity.Auth;
import com.candenizgumus.entity.UserProfile;
import com.candenizgumus.entity.enums.EStatus;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.exceptions.KullaniciServiceException;
import com.candenizgumus.rabbitmq.model.ActivationMailModel;
import com.candenizgumus.rabbitmq.producer.ActivationMailProducer;
import com.candenizgumus.repository.AuthRepository;
import com.candenizgumus.utility.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService
{
    private final AuthRepository authRepository;
    private final UserProfileService userProfileService;
    private final ActivationMailProducer activationMailProducer;

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

        //Auth'a aktivasyon kodu setleme
        auth.setActivationCode(CodeGenerator.generateActivationCode());

        activationMailProducer.convertAndSendToRabbit(ActivationMailModel.builder()
                .email(auth.getEmail())
                .activationCode(auth.getActivationCode())
                .build());
        System.out.println(auth.getActivationCode());

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

    public String activateCode(ActivateCodeRequestDto dto) {
        Auth auth = authRepository.findById(dto.getId())
                .orElseThrow(() -> new KullaniciServiceException(ErrorType.USER_NOT_FOUND));
        if (!auth.getActivationCode().equals(dto.getActivationCode())) {
            throw new KullaniciServiceException(ErrorType.ACTIVATIONCODE_WRONG);
        }

        return statusControl(auth);
    }


    public String statusControl(Auth auth) {
        switch (auth.getStatus()) {
            case ACTIVE -> throw new KullaniciServiceException(ErrorType.ACCOUNT_ALREADY_ACTIVE);
            case PENDING -> {
                //Auth Statusu Aktif Olarak Setle
                auth.setStatus(EStatus.ACTIVE);

                //UserProfile Statusu Aktif Olarak Setle
                userProfileService.findByAuthId(auth.getId()).setStatus(EStatus.ACTIVE);

                authRepository.save(auth);
                return "Aktivasyon Başarılı! Sisteme giriş yapabilirsiniz.";
            }
            case DELETED -> throw new KullaniciServiceException(ErrorType.ACCOUNT_DELETED);
            default -> throw new KullaniciServiceException(ErrorType.INTERNAL_SERVER_ERROR);
        }
    }

}
