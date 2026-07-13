package com.finanzasapp.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class MetaResponse {

    private UUID id;
    private String nombre;
    private String descripcion;
    private String icono;
    private String color;
    private BigDecimal montoObjetivo;
    private BigDecimal montoActual;
    private LocalDate fechaLimite;
    private Boolean completada;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public BigDecimal getMontoObjetivo() { return montoObjetivo; }
    public void setMontoObjetivo(BigDecimal montoObjetivo) { this.montoObjetivo = montoObjetivo; }

    public BigDecimal getMontoActual() { return montoActual; }
    public void setMontoActual(BigDecimal montoActual) { this.montoActual = montoActual; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    public Boolean getCompletada() { return completada; }
    public void setCompletada(Boolean completada) { this.completada = completada; }

    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }

    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}
