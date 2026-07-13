package com.finanzasapp.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public class AbonoMetaRequest {

    private UUID metaId;
    private UUID usuarioId;
    private BigDecimal monto;
    private String notas;

    public UUID getMetaId() { return metaId; }
    public void setMetaId(UUID metaId) { this.metaId = metaId; }

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

}
