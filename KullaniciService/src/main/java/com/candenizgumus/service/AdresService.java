package com.candenizgumus.service;

import com.candenizgumus.repository.AdresRepository;
import com.candenizgumus.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdresService
{
    private final AdresRepository adresRepository;
}
