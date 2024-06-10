package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.HamburgerRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HamburgerService
{
    private final HamburgerRepository  hamburgerRepository;
}
