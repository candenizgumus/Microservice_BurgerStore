package com.candenizgumus.controller;

import com.candenizgumus.dto.request.ActivateCodeRequestDto;
import com.candenizgumus.dto.request.AuthRegisterRequest;
import com.candenizgumus.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.candenizgumus.constants.EndPoints.*;

@RequiredArgsConstructor
@RequestMapping(AUTH)
@RestController
public class AuthController
{
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<String> register(@RequestBody @Valid AuthRegisterRequest dto)
    {
        return ResponseEntity.ok(authService.save(dto));
    }

    @PutMapping(ACTIVATION)
    public ResponseEntity<String> activatecode(@RequestBody ActivateCodeRequestDto dto) {
        return ResponseEntity.ok(authService.activateCode(dto));
    }

}
