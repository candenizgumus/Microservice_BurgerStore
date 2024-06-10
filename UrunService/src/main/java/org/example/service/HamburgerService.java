package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.HamburgerRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class HamburgerService
{
    private final HamburgerRepository  hamburgerRepository;


}
