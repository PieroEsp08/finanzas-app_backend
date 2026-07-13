package com.finanzasapp.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MetaRequest {

    private UUID usuarioId;
    private String nombre;
    private String descripcion;
    private String icono;
    private String color;
    private BigDecimal montoObjetivo;
    private LocalDate fechaLimite;

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

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

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

}
