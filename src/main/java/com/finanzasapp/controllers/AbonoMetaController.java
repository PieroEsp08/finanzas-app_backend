package com.finanzasapp.controllers;

import com.finanzasapp.dto.request.AbonoMetaRequest;
import com.finanzasapp.dto.response.AbonoMetaResponse;
import com.finanzasapp.services.AbonoMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/abonos-meta")
public class AbonoMetaController {

    @Autowired
    private AbonoMetaService abonoMetaService;

    @GetMapping
    public ResponseEntity<List<AbonoMetaResponse>> listarPorMeta(@RequestParam UUID metaId) {
        return ResponseEntity.ok(abonoMetaService.listarPorMeta(metaId));
    }

    @PostMapping
    public ResponseEntity<AbonoMetaResponse> crear(@RequestBody AbonoMetaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(abonoMetaService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbonoMetaResponse> actualizar(@PathVariable UUID id, @RequestBody AbonoMetaRequest request) {
        return ResponseEntity.ok(abonoMetaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AbonoMetaResponse> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(abonoMetaService.eliminar(id));
    }

}
