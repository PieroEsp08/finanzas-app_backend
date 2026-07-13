package com.finanzasapp.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public class PresupuestoRequest {

    private UUID usuarioId;
    private UUID categoriaId;
    private Short mes;
    private Short anio;
    private BigDecimal montoLimite;

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    public UUID getCategoriaId() { return categoriaId; }
    public void setCategoriaId(UUID categoriaId) { this.categoriaId = categoriaId; }

    public Short getMes() { return mes; }
    public void setMes(Short mes) { this.mes = mes; }

    public Short getAnio() { return anio; }
    public void setAnio(Short anio) { this.anio = anio; }

    public BigDecimal getMontoLimite() { return montoLimite; }
    public void setMontoLimite(BigDecimal montoLimite) { this.montoLimite = montoLimite; }

}
