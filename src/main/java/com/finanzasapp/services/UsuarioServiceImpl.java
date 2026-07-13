package com.finanzasapp.services;

import com.finanzasapp.models.Usuario;
import com.finanzasapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario obtenerPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    @Override
    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(UUID id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setTelefono(usuarioActualizado.getTelefono());
            usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            usuario.setOcupacion(usuarioActualizado.getOcupacion());
            usuario.setUbicacion(usuarioActualizado.getUbicacion());
            usuario.setAvatarUrl(usuarioActualizado.getAvatarUrl());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

}
