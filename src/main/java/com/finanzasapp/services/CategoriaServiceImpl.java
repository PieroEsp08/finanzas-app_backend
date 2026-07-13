package com.finanzasapp.services;
import com.finanzasapp.dto.request.CategoriaRequest;
import com.finanzasapp.dto.response.CategoriaResponse;
import com.finanzasapp.models.Categoria;
import com.finanzasapp.models.Usuario;
import com.finanzasapp.repositories.CategoriaRepository;
import com.finanzasapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private CategoriaResponse toResponse(Categoria c) {
        CategoriaResponse response = new CategoriaResponse();
        response.setId(c.getId());
        response.setNombre(c.getNombre());
        response.setTipo(c.getTipo());
        response.setEmoji(c.getEmoji());
        response.setColor(c.getColor());
        response.setEsDefault(c.getEsDefault());
        response.setCreadoEn(c.getCreadoEn());
        response.setActualizadoEn(c.getActualizadoEn());
        return response;
    }

    @Override
    @Transactional
    public List<CategoriaResponse> listarPorUsuario(UUID usuarioId) {
        return categoriaRepository.findActivasPorUsuario(usuarioId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CategoriaResponse> listarPorUsuarioYTipo(UUID usuarioId, String tipo) {
        return categoriaRepository.findActivasPorUsuarioYTipo(usuarioId, tipo)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoriaResponse obtenerPorId(UUID id) {
        Categoria c = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + id));
        return toResponse(c);
    }

    @Override
    @Transactional
    public CategoriaResponse crear(CategoriaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Categoria c = new Categoria();
        c.setUsuario(usuario);
        c.setNombre(request.getNombre());
        c.setTipo(request.getTipo());
        c.setEmoji(request.getEmoji());
        c.setColor(request.getColor());
        c.setEsDefault(false);

        return toResponse(categoriaRepository.save(c));
    }

    @Override
    @Transactional
    public CategoriaResponse actualizar(UUID id, CategoriaRequest request) {
        Categoria c = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + id));

        if (Boolean.TRUE.equals(c.getEsDefault())) {
            throw new RuntimeException("No se puede editar una categoría predefinida");
        }

        c.setNombre(request.getNombre());
        c.setTipo(request.getTipo());
        c.setEmoji(request.getEmoji());
        c.setColor(request.getColor());

        return toResponse(categoriaRepository.save(c));
    }

    @Override
    @Transactional
    public CategoriaResponse eliminar(UUID id) {
        Categoria c = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + id));

        if (Boolean.TRUE.equals(c.getEsDefault())) {
            throw new RuntimeException("No se puede eliminar una categoría predefinida");
        }

        c.setEliminadoEn(OffsetDateTime.now());
        return toResponse(categoriaRepository.save(c));
    }

}
