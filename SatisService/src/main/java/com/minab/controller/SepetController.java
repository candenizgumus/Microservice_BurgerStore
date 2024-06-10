package com.minab.controller;

import static com.minab.constants.EndPoints.*;

import com.minab.entity.enums.ECikartilacakUrunMalzemeleri;
import com.minab.entity.enums.EExtraMalzeme;
import com.minab.entity.enums.EPismeDerecesi;
import com.minab.entity.enums.ESos;
import com.minab.service.SepetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping(SEPET)
@RestController
public class SepetController {
    private final SepetService sepetService;

    @PostMapping(SAVE)
    public ResponseEntity<String> sepeteHamburgerEkle(@RequestParam Set<ECikartilacakUrunMalzemeleri> cikarilacakMalzemeler, @RequestParam Set<EExtraMalzeme> ekstraMalzemeler, @RequestParam EPismeDerecesi pismeDerecesi, @RequestParam Set<ESos> soslar, @RequestParam Double hamburgerBirimFiyati, @RequestParam String ad)
    {
        return ResponseEntity.ok(sepetService.save(cikarilacakMalzemeler, ekstraMalzemeler, pismeDerecesi,soslar, hamburgerBirimFiyati, ad));
    }

}
