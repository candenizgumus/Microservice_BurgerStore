package com.candenizgumus.service;

import com.candenizgumus.repository.AuthRepository;
import com.candenizgumus.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserProfileService
{
    private final UserProfileRepository userProfileRepository;
}
