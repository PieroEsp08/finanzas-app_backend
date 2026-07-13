package com.finanzasapp.controllers;

import com.finanzasapp.dto.request.PresupuestoRequest;
import com.finanzasapp.dto.response.PresupuestoResponse;
import com.finanzasapp.models.Presupuesto;
import com.finanzasapp.services.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/presupuestos")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;

    @GetMapping
    public ResponseEntity<List<PresupuestoResponse>> listarPorUsuario(@RequestParam UUID usuarioId) {
        return ResponseEntity.ok(presupuestoService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<PresupuestoResponse>> listarPorUsuarioYPeriodo(
            @RequestParam UUID usuarioId,
            @RequestParam Short mes,
            @RequestParam Short anio) {
        return ResponseEntity.ok(presupuestoService.listarPorUsuarioYPeriodo(usuarioId, mes, anio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresupuestoResponse> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(presupuestoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PresupuestoResponse> crear(@RequestBody PresupuestoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(presupuestoService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PresupuestoResponse> actualizar(@PathVariable UUID id, @RequestBody PresupuestoRequest request) {
        return ResponseEntity.ok(presupuestoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PresupuestoResponse> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(presupuestoService.eliminar(id));
    }

}
