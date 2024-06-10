package com.candenizgumus.controller;

import com.candenizgumus.dto.request.AdresSaveRequest;
import com.candenizgumus.service.AdresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.candenizgumus.constants.EndPoints.*;


@RequiredArgsConstructor
@RequestMapping(ADRES)
@RestController
public class AdresController
{
    private final AdresService adresService;

    @PostMapping(SAVE)
    public ResponseEntity<String> save(AdresSaveRequest dto)
    {
        return ResponseEntity.ok(adresService.save(dto));
    }

}
