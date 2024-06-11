package org.example.controller;

import lombok.RequiredArgsConstructor;

import org.example.dto.request.HamburgerSaveRequest;
import org.example.service.HamburgerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.constants.EndPoints.*;

@RestController
@RequestMapping(HAMBURGER)
@RequiredArgsConstructor
public class HamburgerController
{
    private final HamburgerService hamburgerService;

    @PostMapping(SAVE)
    public ResponseEntity<String> save(HamburgerSaveRequest dto)
    {
        return ResponseEntity.ok(hamburgerService.save(dto));
    }


}
