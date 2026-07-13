package com.finanzasapp.dto.request;

import java.util.UUID;

public class CategoriaRequest {

    private UUID usuarioId;
    private String nombre;
    private String tipo;
    private String emoji;
    private String color;

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

}
