package com.finanzasapp.services;

import com.finanzasapp.models.Usuario;

import java.util.UUID;

public interface UsuarioService {

    Usuario obtenerPorId(UUID id);

    Usuario crear(Usuario usuario);

    Usuario actualizar(UUID id, Usuario usuarioActualizado);

}
