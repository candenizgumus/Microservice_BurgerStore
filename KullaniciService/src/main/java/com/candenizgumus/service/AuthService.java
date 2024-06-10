package com.candenizgumus.service;

import com.candenizgumus.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService
{
    private final AuthRepository authRepository;
}
