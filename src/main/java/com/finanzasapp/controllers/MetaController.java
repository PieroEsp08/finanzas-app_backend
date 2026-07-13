package com.finanzasapp.controllers;

import com.finanzasapp.dto.request.MetaRequest;
import com.finanzasapp.dto.response.MetaResponse;
import com.finanzasapp.services.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping
    public ResponseEntity<List<MetaResponse>> listarPorUsuario(@RequestParam UUID usuarioId) {
        return ResponseEntity.ok(metaService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<MetaResponse>> listarPorUsuarioYEstado(
            @RequestParam UUID usuarioId,
            @RequestParam Boolean completada) {
        return ResponseEntity.ok(metaService.listarPorUsuarioYEstado(usuarioId, completada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaResponse> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(metaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<MetaResponse> crear(@RequestBody MetaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metaService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetaResponse> actualizar(@PathVariable UUID id, @RequestBody MetaRequest request) {
        return ResponseEntity.ok(metaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MetaResponse> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(metaService.eliminar(id));
    }

}
