package com.finanzasapp.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class AbonoMetaResponse {

    private UUID id;
    private UUID metaId;
    private BigDecimal monto;
    private String notas;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getMetaId() { return metaId; }
    public void setMetaId(UUID metaId) { this.metaId = metaId; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }

    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }

}
