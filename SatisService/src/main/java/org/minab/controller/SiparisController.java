package org.minab.controller;


import org.minab.service.SiparisService;
import lombok.RequiredArgsConstructor;
import org.minab.constants.EndPoints;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(EndPoints.SIPARIS)
@RestController
public class SiparisController {
    private final SiparisService siparisService;

    @PostMapping(EndPoints.SEPETIONAYLA)
    public ResponseEntity<String> save (@RequestParam Long sepetId){
        return ResponseEntity.ok(siparisService.save(sepetId));
    }


}
