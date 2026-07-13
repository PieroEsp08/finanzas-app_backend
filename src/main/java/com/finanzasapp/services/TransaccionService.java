package com.finanzasapp.services;

import com.finanzasapp.dto.request.TransaccionRequest;
import com.finanzasapp.dto.response.TransaccionResponse;
import com.finanzasapp.models.Transaccion;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransaccionService {

    List<TransaccionResponse> listarPorUsuario(UUID usuarioId);
    List<TransaccionResponse> listarPorUsuarioYTipo(UUID usuarioId, String tipo);
    List<TransaccionResponse> listarPorRangoFechas(UUID usuarioId, LocalDate desde, LocalDate hasta);
    TransaccionResponse obtenerPorId(UUID id);
    TransaccionResponse crear(TransaccionRequest request);
    TransaccionResponse actualizar(UUID id, TransaccionRequest request);
    TransaccionResponse eliminar(UUID id);
}
