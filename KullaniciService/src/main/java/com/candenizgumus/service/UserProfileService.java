package com.candenizgumus.service;

import com.candenizgumus.entity.UserProfile;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.exceptions.KullaniciServiceException;
import com.candenizgumus.repository.AuthRepository;
import com.candenizgumus.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
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


}
