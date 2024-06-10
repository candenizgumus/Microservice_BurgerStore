package com.minab.controller;

import static com.minab.constants.EndPoints.*;

import com.minab.service.SepetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(SEPET)
@RestController
public class SepetController {
    private final SepetService sepetService;


}
