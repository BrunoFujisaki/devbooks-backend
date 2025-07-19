package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.estatisticas.EstatisticasDTO;
import com.BrunoFujisaki.devbooks_backend.service.EstatisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estatisticas")
@RequiredArgsConstructor
public class EstatisticasController {

    private final EstatisticasService service;

    @GetMapping
    public ResponseEntity<EstatisticasDTO> getEstatisticas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getEstatisticas());
    }
}
