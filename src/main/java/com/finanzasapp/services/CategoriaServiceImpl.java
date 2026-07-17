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
        CategoriaResponse r = new CategoriaResponse();
        r.setId(c.getId());
        r.setNombre(c.getNombre());
        r.setTipo(c.getTipo());
        r.setEmoji(c.getEmoji());
        r.setColor(c.getColor());
        r.setEsDefault(c.getEsDefault());
        r.setCreadoEn(c.getCreadoEn());
        r.setActualizadoEn(c.getActualizadoEn());
        return r;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponse> listarPorUsuario(UUID usuarioId) {
        return categoriaRepository.findActivasPorUsuario(usuarioId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponse> listarPorUsuarioYTipo(UUID usuarioId, String tipo) {
        return categoriaRepository.findActivasPorUsuarioYTipo(usuarioId, tipo)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponse obtenerPorId(UUID id) {
        return toResponse(
                categoriaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id))
        );
    }

    @Override
    @Transactional
    public CategoriaResponse crear(CategoriaRequest request) {
        Usuario usuario = usuarioRepository.getReferenceById(request.getUsuarioId());

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
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));

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
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));

        if (Boolean.TRUE.equals(c.getEsDefault())) {
            throw new RuntimeException("No se puede eliminar una categoría predefinida");
        }

        c.setEliminadoEn(OffsetDateTime.now());
        categoriaRepository.save(c);
        
        CategoriaResponse r = new CategoriaResponse();
        r.setId(c.getId());
        return r;
    }
}