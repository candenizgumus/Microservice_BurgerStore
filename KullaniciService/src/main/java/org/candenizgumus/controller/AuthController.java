package org.candenizgumus.controller;

import org.candenizgumus.dto.request.AuthRegisterRequest;
import org.candenizgumus.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.candenizgumus.constants.EndPoints.AUTH;
import static org.candenizgumus.constants.EndPoints.REGISTER;

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

}
