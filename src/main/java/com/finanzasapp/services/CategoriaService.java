package com.finanzasapp.services;

import com.finanzasapp.dto.request.CategoriaRequest;
import com.finanzasapp.dto.response.CategoriaResponse;

import java.util.List;
import java.util.UUID;

public interface CategoriaService {

    List<CategoriaResponse> listarPorUsuario(UUID usuarioId);
    List<CategoriaResponse> listarPorUsuarioYTipo(UUID usuarioId, String tipo);
    CategoriaResponse obtenerPorId(UUID id);
    CategoriaResponse crear(CategoriaRequest request);
    CategoriaResponse actualizar(UUID id, CategoriaRequest request);
    CategoriaResponse eliminar(UUID id);

}
