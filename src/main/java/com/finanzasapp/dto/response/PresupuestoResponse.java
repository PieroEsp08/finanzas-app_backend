package com.finanzasapp.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class PresupuestoResponse {

    private UUID id;
    private Short mes;
    private Short anio;
    private BigDecimal montoLimite;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;
    private UUID categoriaId;
    private String categoriaNombre;
    private String categoriaEmoji;
    private String categoriaColor;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Short getMes() { return mes; }
    public void setMes(Short mes) { this.mes = mes; }

    public Short getAnio() { return anio; }
    public void setAnio(Short anio) { this.anio = anio; }

    public BigDecimal getMontoLimite() { return montoLimite; }
    public void setMontoLimite(BigDecimal montoLimite) { this.montoLimite = montoLimite; }

    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }

    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }

    public UUID getCategoriaId() { return categoriaId; }
    public void setCategoriaId(UUID categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNombre() { return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }

    public String getCategoriaEmoji() { return categoriaEmoji; }
    public void setCategoriaEmoji(String categoriaEmoji) { this.categoriaEmoji = categoriaEmoji; }

    public String getCategoriaColor() { return categoriaColor; }
    public void setCategoriaColor(String categoriaColor) { this.categoriaColor = categoriaColor; }

}
