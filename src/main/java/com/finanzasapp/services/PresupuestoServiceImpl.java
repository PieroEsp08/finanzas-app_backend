package com.finanzasapp.services;

import com.finanzasapp.dto.request.PresupuestoRequest;
import com.finanzasapp.dto.response.PresupuestoResponse;
import com.finanzasapp.models.Categoria;
import com.finanzasapp.models.Presupuesto;
import com.finanzasapp.models.Usuario;
import com.finanzasapp.repositories.CategoriaRepository;
import com.finanzasapp.repositories.PresupuestoRepository;
import com.finanzasapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PresupuestoServiceImpl implements PresupuestoService{

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private PresupuestoResponse toResponse(Presupuesto p) {
        PresupuestoResponse response = new PresupuestoResponse();
        response.setId(p.getId());
        response.setMes(p.getMes());
        response.setAnio(p.getAnio());
        response.setMontoLimite(p.getMontoLimite());
        response.setCreadoEn(p.getCreadoEn());
        response.setActualizadoEn(p.getActualizadoEn());

        if (p.getCategoria() != null) {
            response.setCategoriaId(p.getCategoria().getId());
            response.setCategoriaNombre(p.getCategoria().getNombre());
            response.setCategoriaEmoji(p.getCategoria().getEmoji());
            response.setCategoriaColor(p.getCategoria().getColor());
        }

        return response;
    }

    @Override
    @Transactional
    public List<PresupuestoResponse> listarPorUsuario(UUID usuarioId) {
        return presupuestoRepository.findActivosPorUsuario(usuarioId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PresupuestoResponse> listarPorUsuarioYPeriodo(UUID usuarioId, Short mes, Short anio) {
        return presupuestoRepository.findActivosPorUsuarioYPeriodo(usuarioId, mes, anio)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PresupuestoResponse obtenerPorId(UUID id) {
        Presupuesto p = presupuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado con id " + id));
        return toResponse(p);
    }

    @Override
    @Transactional
    public PresupuestoResponse crear(PresupuestoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Presupuesto p = new Presupuesto();
        p.setUsuario(usuario);
        p.setCategoria(categoria);
        p.setMes(request.getMes());
        p.setAnio(request.getAnio());
        p.setMontoLimite(request.getMontoLimite());

        return toResponse(presupuestoRepository.save(p));
    }

    @Override
    @Transactional
    public PresupuestoResponse actualizar(UUID id, PresupuestoRequest request) {
        Presupuesto p = presupuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado con id " + id));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        p.setCategoria(categoria);
        p.setMes(request.getMes());
        p.setAnio(request.getAnio());
        p.setMontoLimite(request.getMontoLimite());

        return toResponse(presupuestoRepository.save(p));
    }

    @Override
    @Transactional
    public PresupuestoResponse eliminar(UUID id) {
        Presupuesto p = presupuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado con id " + id));
        p.setEliminadoEn(OffsetDateTime.now());
        return toResponse(presupuestoRepository.save(p));
    }

}
