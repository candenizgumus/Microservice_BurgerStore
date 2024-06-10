package com.minab.service;

import com.minab.repository.SepetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SepetService {
    private final SepetRepository sepetRepository;
}
