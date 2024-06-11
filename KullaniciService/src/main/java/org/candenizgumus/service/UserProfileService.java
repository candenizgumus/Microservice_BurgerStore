package org.candenizgumus.service;

import org.candenizgumus.entity.UserProfile;
import org.candenizgumus.exceptions.ErrorType;
import org.candenizgumus.exceptions.KullaniciServiceException;
import org.candenizgumus.repository.UserProfileRepository;
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
}
