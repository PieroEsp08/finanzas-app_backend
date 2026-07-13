package com.finanzasapp.services;

import com.finanzasapp.dto.request.AbonoMetaRequest;
import com.finanzasapp.dto.response.AbonoMetaResponse;

import java.util.List;
import java.util.UUID;

public interface AbonoMetaService {

    List<AbonoMetaResponse> listarPorMeta(UUID metaId);
    AbonoMetaResponse crear(AbonoMetaRequest request);
    AbonoMetaResponse actualizar(UUID id, AbonoMetaRequest request);
    AbonoMetaResponse eliminar(UUID id);

}
