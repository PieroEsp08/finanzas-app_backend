package com.finanzasapp.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CategoriaResponse {

    private UUID id;
    private String nombre;
    private String tipo;
    private String emoji;
    private String color;
    private Boolean esDefault;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Boolean getEsDefault() { return esDefault; }
    public void setEsDefault(Boolean esDefault) { this.esDefault = esDefault; }

    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }

    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }

}
