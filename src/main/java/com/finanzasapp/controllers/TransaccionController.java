package com.finanzasapp.controllers;

import com.finanzasapp.dto.request.TransaccionRequest;
import com.finanzasapp.dto.response.TransaccionResponse;
import com.finanzasapp.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public ResponseEntity<List<TransaccionResponse>> listarPorUsuario(@RequestParam UUID usuarioId) {
        return ResponseEntity.ok(transaccionService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<TransaccionResponse>> listarPorUsuarioYTipo(
            @RequestParam UUID usuarioId,
            @RequestParam String tipo) {
        return ResponseEntity.ok(transaccionService.listarPorUsuarioYTipo(usuarioId, tipo));
    }

    @GetMapping("/rango")
    public ResponseEntity<List<TransaccionResponse>> listarPorRango(
            @RequestParam UUID usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return ResponseEntity.ok(transaccionService.listarPorRangoFechas(usuarioId, desde, hasta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionResponse> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(transaccionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<TransaccionResponse> crear(@RequestBody TransaccionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transaccionService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransaccionResponse> actualizar(@PathVariable UUID id, @RequestBody TransaccionRequest request) {
        return ResponseEntity.ok(transaccionService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransaccionResponse> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(transaccionService.eliminar(id));
    }

}
