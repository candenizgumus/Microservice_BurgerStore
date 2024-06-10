package com.minab.controller;


import com.minab.service.SiparisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.minab.constants.EndPoints.SIPARIS;

@RequiredArgsConstructor
@RequestMapping(SIPARIS)
@RestController
public class SiparisController {
    private final SiparisService siparisService;
}
