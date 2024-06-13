package org.example.controller;
import lombok.RequiredArgsConstructor;

import org.example.config.model.HamburgerModel;
import org.example.dto.request.HamburgerSaveRequest;
import org.example.entity.Hamburger;
import org.example.service.HamburgerService;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.constants.EndPoints.*;


@RestController
@RequestMapping(HAMBURGER)
@RequiredArgsConstructor
public class HamburgerController
{
    private final HamburgerService hamburgerService;

    @PostMapping(SAVE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> save(@RequestBody HamburgerSaveRequest dto)
    {
        return ResponseEntity.ok(hamburgerService.save(dto));
    }

    @GetMapping(FINDBYID)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<HamburgerModel> findById(@RequestParam Long hamburgerId)
    {
        return ResponseEntity.ok(hamburgerService.find(hamburgerId));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<HamburgerModel>> findAll() {
        return ResponseEntity.ok(hamburgerService.findAll());
    }


}
