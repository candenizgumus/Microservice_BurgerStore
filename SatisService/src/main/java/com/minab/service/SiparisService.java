package com.minab.service;

import com.minab.repository.SiparisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SiparisService {
    private final SiparisRepository siparisRepository;
}
