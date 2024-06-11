package org.candenizgumus.controller;




import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.candenizgumus.dto.request.ActivateCodeRequestDto;
import org.candenizgumus.dto.request.AuthRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

}
