package org.minab.controller;


import org.minab.dto.SiparisSaveRequestDto;
import org.minab.service.SiparisService;
import lombok.RequiredArgsConstructor;
import org.minab.constants.EndPoints;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(EndPoints.SIPARIS)
@RestController
public class SiparisController {
    private final SiparisService siparisService;

    @GetMapping(EndPoints.SAVE)
    public ResponseEntity<String> save (SiparisSaveRequestDto dto){
        return ResponseEntity.ok(siparisService.save(dto));
    }
}
