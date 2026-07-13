package com.finanzasapp.services;

import com.finanzasapp.dto.request.MetaRequest;
import com.finanzasapp.dto.response.MetaResponse;
import com.finanzasapp.models.Meta;
import com.finanzasapp.models.Usuario;
import com.finanzasapp.repositories.MetaRepository;
import com.finanzasapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MetaServiceImpl implements MetaService{

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private MetaResponse toResponse(Meta m) {
        MetaResponse response = new MetaResponse();
        response.setId(m.getId());
        response.setNombre(m.getNombre());
        response.setDescripcion(m.getDescripcion());
        response.setIcono(m.getIcono());
        response.setColor(m.getColor());
        response.setMontoObjetivo(m.getMontoObjetivo());
        response.setMontoActual(m.getMontoActual());
        response.setFechaLimite(m.getFechaLimite());
        response.setCompletada(m.getCompletada());
        response.setCreadoEn(m.getCreadoEn());
        response.setActualizadoEn(m.getActualizadoEn());
        return response;
    }

    @Override
    @Transactional
    public List<MetaResponse> listarPorUsuario(UUID usuarioId) {
        return metaRepository.findActivasPorUsuario(usuarioId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MetaResponse> listarPorUsuarioYEstado(UUID usuarioId, Boolean completada) {
        return metaRepository.findActivasPorUsuarioYEstado(usuarioId, completada)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MetaResponse obtenerPorId(UUID id) {
        Meta m = metaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta no encontrada con id " + id));
        return toResponse(m);
    }

    @Override
    @Transactional
    public MetaResponse crear(MetaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Meta m = new Meta();
        m.setUsuario(usuario);
        m.setNombre(request.getNombre());
        m.setDescripcion(request.getDescripcion());
        m.setIcono(request.getIcono());
        m.setColor(request.getColor());
        m.setMontoObjetivo(request.getMontoObjetivo());
        m.setFechaLimite(request.getFechaLimite());

        return toResponse(metaRepository.save(m));
    }

    @Override
    @Transactional
    public MetaResponse actualizar(UUID id, MetaRequest request) {
        Meta m = metaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta no encontrada con id " + id));

        m.setNombre(request.getNombre());
        m.setDescripcion(request.getDescripcion());
        m.setIcono(request.getIcono());
        m.setColor(request.getColor());
        m.setMontoObjetivo(request.getMontoObjetivo());
        m.setFechaLimite(request.getFechaLimite());

        return toResponse(metaRepository.save(m));
    }

    @Override
    @Transactional
    public MetaResponse eliminar(UUID id) {
        Meta m = metaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta no encontrada con id " + id));
        m.setEliminadoEn(OffsetDateTime.now());
        return toResponse(metaRepository.save(m));
    }
}
