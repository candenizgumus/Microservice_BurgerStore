package com.candenizgumus.controller;

import com.candenizgumus.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.candenizgumus.constants.EndPoints.AUTH;

@RequiredArgsConstructor
@RequestMapping(AUTH)
@RestController
public class AuthController
{
    private final AuthService authService;
}
