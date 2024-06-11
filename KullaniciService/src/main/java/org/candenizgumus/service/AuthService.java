package org.candenizgumus.service;

import org.candenizgumus.dto.request.ActivateCodeRequestDto;
import org.candenizgumus.dto.request.AuthRegisterRequest;
import org.candenizgumus.entity.Auth;
import org.candenizgumus.entity.UserProfile;
import org.candenizgumus.entity.enums.EStatus;
import org.candenizgumus.exceptions.ErrorType;
import org.candenizgumus.exceptions.KullaniciServiceException;
import org.candenizgumus.rabbitmq.model.ActivationMailModel;
import org.candenizgumus.rabbitmq.producer.ActivationMailProducer;
import org.candenizgumus.repository.AuthRepository;
import org.candenizgumus.utility.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService
{
    private final AuthRepository authRepository;
    private final UserProfileService userProfileService;
    private final ActivationMailProducer activationMailProducer;
    private final RabbitTemplate rabbitTemplate;

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

        //Sepet nesnesi olusturma ve sepet id alma

        Object sepetId = rabbitTemplate.convertSendAndReceive("direct.exchange", "key.savesepet", userProfile.getId());


        userProfile.setSepetId((Long) sepetId);
        userProfileService.save(userProfile);
        return "Kayıt Tamamlandı";

    }

    public String activateCode(ActivateCodeRequestDto dto) {
        Auth auth = authRepository.findByEmail(dto.getEmail())
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

    public List<Auth> findAll()
    {
        return authRepository.findAll();
    }
}
