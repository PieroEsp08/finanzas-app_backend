package com.finanzasapp.controllers;

import com.finanzasapp.dto.request.CategoriaRequest;
import com.finanzasapp.dto.response.CategoriaResponse;
import com.finanzasapp.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarPorUsuario(@RequestParam UUID usuarioId) {
        return ResponseEntity.ok(categoriaService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<CategoriaResponse>> listarPorUsuarioYTipo(
            @RequestParam UUID usuarioId,
            @RequestParam String tipo) {
        return ResponseEntity.ok(categoriaService.listarPorUsuarioYTipo(usuarioId, tipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(@RequestBody CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizar(@PathVariable UUID id, @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaResponse> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(categoriaService.eliminar(id));
    }

}
