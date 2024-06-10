package com.minab.controller;


import com.minab.dto.SiparisSaveRequestDto;
import com.minab.entity.Siparis;
import com.minab.service.SiparisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.minab.constants.EndPoints.SAVE;
import static com.minab.constants.EndPoints.SIPARIS;

@RequiredArgsConstructor
@RequestMapping(SIPARIS)
@RestController
public class SiparisController {
    private final SiparisService siparisService;

    @GetMapping(SAVE)
    public ResponseEntity<String> save (SiparisSaveRequestDto dto){
        return ResponseEntity.ok(siparisService.save(dto));
    }
}
