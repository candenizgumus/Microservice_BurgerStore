package org.minab.controller;

import org.minab.entity.Sepet;
import org.minab.entity.enums.ECikartilacakUrunMalzemeleri;
import org.minab.entity.enums.EExtraMalzeme;
import org.minab.entity.enums.EPismeDerecesi;
import org.minab.entity.enums.ESos;
import org.minab.service.SepetService;
import lombok.RequiredArgsConstructor;
import org.minab.constants.EndPoints;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping(EndPoints.SEPET)
@RestController
public class SepetController {
    private final SepetService sepetService;

    @PostMapping(EndPoints.SEPETEHAMBURGEREKLE)
    public ResponseEntity<String> sepeteHamburgerEkle(@RequestParam Long sepetId,@RequestParam Long hamburgerId, Integer adet,@RequestParam Set<ECikartilacakUrunMalzemeleri> cikarilacakMalzemeler, @RequestParam Set<EExtraMalzeme> ekstraMalzemeler, @RequestParam EPismeDerecesi pismeDerecesi, @RequestParam Set<ESos> soslar)
    {
        return ResponseEntity.ok(sepetService.sepeteHamburgerEkle( sepetId,hamburgerId, adet, cikarilacakMalzemeler, ekstraMalzemeler, pismeDerecesi, soslar));
    }

    @GetMapping(EndPoints.SEPETIMIGORUNTULE)
    public ResponseEntity<Sepet> sepetiGoruntule(@RequestParam Long sepetId)
    {
        return ResponseEntity.ok(sepetService.sepetiGoruntule(sepetId));
    }

    @DeleteMapping(EndPoints.SEPETITEMIZLE)
    public ResponseEntity<String> sepetiTemizle(@RequestParam Long sepetId)
    {
        return ResponseEntity.ok(sepetService.sepetiTemizle(sepetId));
    }

}
