package com.finanzasapp.services;

import com.finanzasapp.dto.request.MetaRequest;
import com.finanzasapp.dto.response.MetaResponse;

import java.util.List;
import java.util.UUID;

public interface MetaService  {

    List<MetaResponse> listarPorUsuario(UUID usuarioId);
    List<MetaResponse> listarPorUsuarioYEstado(UUID usuarioId, Boolean completada);
    MetaResponse obtenerPorId(UUID id);
    MetaResponse crear(MetaRequest request);
    MetaResponse actualizar(UUID id, MetaRequest request);
    MetaResponse eliminar(UUID id);


}
