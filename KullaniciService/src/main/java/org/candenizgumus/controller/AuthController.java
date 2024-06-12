package org.candenizgumus.controller;




import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.candenizgumus.dto.request.ActivateCodeRequestDto;
import org.candenizgumus.dto.request.AuthRegisterRequest;
import org.candenizgumus.dto.request.LoginRequestDto;
import org.candenizgumus.dto.request.UpdatePasswordRequestDto;
import org.candenizgumus.entity.Auth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.candenizgumus.constants.EndPoints.*;


@RequiredArgsConstructor
@RequestMapping(AUTH)
@RestController
public class AuthController
{
    private final org.candenizgumus.service.AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<String> register(@RequestBody @Valid AuthRegisterRequest dto)
    {
        return ResponseEntity.ok(authService.save(dto));
    }

    @PutMapping(ACTIVATION)
    public ResponseEntity<String> activatecode(@RequestBody ActivateCodeRequestDto dto) {
        return ResponseEntity.ok(authService.activateCode(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Auth>> findAll()
    {
        return ResponseEntity.ok(authService.findAll());
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> dologin(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @GetMapping(SIFREMIUNUTTUM)
    public ResponseEntity<String> sifremiUnuttum(@RequestParam String email) {
        authService.sifremiUnuttum(email);
        return ResponseEntity.ok("Şifre değiştirme kodunuz "+ email + "adresine gönderildi.Şifrenizi onaylamak için updatePassword bölümüne gidiniz.");
    }

    @PutMapping(UPDATEPASSWORD)
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequestDto dto) {
        return ResponseEntity.ok(authService.updatePassword(dto));
    }
}
