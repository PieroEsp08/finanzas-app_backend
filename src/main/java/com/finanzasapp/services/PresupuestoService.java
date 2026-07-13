package com.finanzasapp.services;

import com.finanzasapp.dto.request.PresupuestoRequest;
import com.finanzasapp.dto.response.PresupuestoResponse;

import java.util.List;
import java.util.UUID;

public interface PresupuestoService {

    List<PresupuestoResponse> listarPorUsuario(UUID usuarioId);
    List<PresupuestoResponse> listarPorUsuarioYPeriodo(UUID usuarioId, Short mes, Short anio);
    PresupuestoResponse obtenerPorId(UUID id);
    PresupuestoResponse crear(PresupuestoRequest request);
    PresupuestoResponse actualizar(UUID id, PresupuestoRequest request);
    PresupuestoResponse eliminar(UUID id);

}
