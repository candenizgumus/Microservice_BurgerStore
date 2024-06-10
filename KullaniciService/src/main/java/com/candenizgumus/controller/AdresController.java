package com.candenizgumus.controller;

import com.candenizgumus.service.AdresService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.candenizgumus.constants.EndPoints.*;


@RequiredArgsConstructor
@RequestMapping(ADRES)
@RestController
public class AdresController
{
    private final AdresService adresService;

}
