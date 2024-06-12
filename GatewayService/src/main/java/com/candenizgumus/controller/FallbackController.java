package com.candenizgumus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController
{
    @GetMapping("/kullanici")
    public ResponseEntity<String> getFallbackAuth(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kullanici Service şu an hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }

    @GetMapping("/urun")
    public ResponseEntity<String> getFallBackPostProfile(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Urun Service şu an hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }
    @GetMapping("/email")
    public ResponseEntity<String> getFallBackPostProfile23(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Service şu an hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }
}
