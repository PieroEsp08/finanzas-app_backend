package com.finanzasapp.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
public class TransaccionResponse {

    private UUID id;
    private String tipo;
    private String concepto;
    private BigDecimal monto;
    private LocalDate fecha;
    private String notas;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;
    private OffsetDateTime eliminadoEn;
    private UUID categoriaId;
    private String categoriaNombre;
    private String categoriaEmoji;
    private String categoriaColor;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }

    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }

    public OffsetDateTime getEliminadoEn() { return eliminadoEn; } // 👈 agrega esto
    public void setEliminadoEn(OffsetDateTime eliminadoEn) { this.eliminadoEn = eliminadoEn; } // 👈 agrega esto

    public UUID getCategoriaId() { return categoriaId; }
    public void setCategoriaId(UUID categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNombre() { return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }

    public String getCategoriaEmoji() { return categoriaEmoji; }
    public void setCategoriaEmoji(String categoriaEmoji) { this.categoriaEmoji = categoriaEmoji; }

    public String getCategoriaColor() { return categoriaColor; }
    public void setCategoriaColor(String categoriaColor) { this.categoriaColor = categoriaColor; }
}