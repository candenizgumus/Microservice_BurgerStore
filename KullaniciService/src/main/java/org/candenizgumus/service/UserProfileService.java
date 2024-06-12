package org.candenizgumus.service;

import org.candenizgumus.config.model.UserProfileUpdateBalanceModel;
import org.candenizgumus.entity.UserProfile;
import org.candenizgumus.exceptions.ErrorType;
import org.candenizgumus.exceptions.KullaniciServiceException;
import org.candenizgumus.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserProfileService
{
    private final UserProfileRepository userProfileRepository;

    public void save(UserProfile userProfile)
    {
        userProfileRepository.save(userProfile);
    }

    public UserProfile findById(Long userId)
    {
        return userProfileRepository.findById(userId).orElseThrow(() -> new KullaniciServiceException(ErrorType.USER_NOT_FOUND));
    }
    public UserProfile findByAuthId(Long authId)
    {
        return userProfileRepository.findByAuthId(authId).orElseThrow(() -> new KullaniciServiceException(ErrorType.USER_NOT_FOUND));
    }

    @RabbitListener(queues = "finduserprofileandupdatebalance")
    public void find(UserProfileUpdateBalanceModel dto)
    {
        //UserProfile bulma
        UserProfile userProfile = userProfileRepository.findById(dto.getUserProfileId()).orElseThrow(() -> new KullaniciServiceException(ErrorType.USER_NOT_FOUND));

        //Kullanıcı bakiyesinin kontrolu ve bakiye güncelleme
        if (userProfile.getBakiye()+userProfile.getPuan()<dto.getToplamTutar())
        {
            System.out.println("Bakiye yetersiz"); //TODO BURADA HATA FIRLATINCA RABBİT SONSUZ DÖNGÜYE GİRİYOR.
        }else
        {


            //Puan azaltma (Eğer puan toplamtutardan büyükse puanı düş)
            if (userProfile.getPuan()>dto.getToplamTutar()) {
                userProfile.setPuan(userProfile.getPuan()-dto.getToplamTutar());
            }
            else
            {
                userProfile.setPuan(0.0);
                userProfile.setBakiye(userProfile.getBakiye()+userProfile.getPuan()-dto.getToplamTutar());
            }

            userProfileRepository.save(userProfile);
        }
    }


}
