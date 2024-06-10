package org.example.controller;

import lombok.RequiredArgsConstructor;

import org.example.service.HamburgerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.constants.EndPoints.*;

@RestController
@RequestMapping(HAMBURGER)
@RequiredArgsConstructor
public class HamburgerController
{
    private final HamburgerService hamburgerService;



}
