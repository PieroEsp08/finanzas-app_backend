package com.finanzasapp.services;

import com.finanzasapp.dto.request.TransaccionRequest;
import com.finanzasapp.dto.response.TransaccionResponse;
import com.finanzasapp.models.Categoria;
import com.finanzasapp.models.Transaccion;
import com.finanzasapp.models.Usuario;
import com.finanzasapp.repositories.CategoriaRepository;
import com.finanzasapp.repositories.TransaccionRepository;
import com.finanzasapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private TransaccionResponse toResponse(Transaccion t) {
        TransaccionResponse response = new TransaccionResponse();
        response.setId(t.getId());
        response.setTipo(t.getTipo());
        response.setConcepto(t.getConcepto());
        response.setMonto(t.getMonto());
        response.setFecha(t.getFecha());
        response.setNotas(t.getNotas());
        response.setCreadoEn(t.getCreadoEn());
        response.setActualizadoEn(t.getActualizadoEn());

        if (t.getCategoria() != null) {
            response.setCategoriaId(t.getCategoria().getId());
            response.setCategoriaNombre(t.getCategoria().getNombre());
            response.setCategoriaEmoji(t.getCategoria().getEmoji());
            response.setCategoriaColor(t.getCategoria().getColor());
        }

        return response;
    }

    @Override
    @Transactional
    public List<TransaccionResponse> listarPorUsuario(UUID usuarioId) {
        return transaccionRepository.findActivasPorUsuario(usuarioId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TransaccionResponse> listarPorUsuarioYTipo(UUID usuarioId, String tipo) {
        return transaccionRepository.findActivasPorUsuarioYTipo(usuarioId, tipo)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TransaccionResponse> listarPorRangoFechas(UUID usuarioId, LocalDate desde, LocalDate hasta) {
        return transaccionRepository.findByUsuarioYRangoFechas(usuarioId, desde, hasta)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransaccionResponse obtenerPorId(UUID id) {
        Transaccion t = transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con id " + id));
        return toResponse(t);
    }

    @Override
    @Transactional
    public TransaccionResponse crear(TransaccionRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Transaccion t = new Transaccion();
        t.setUsuario(usuario);
        t.setCategoria(categoria);
        t.setTipo(request.getTipo());
        t.setConcepto(request.getConcepto());
        t.setMonto(request.getMonto());
        t.setFecha(request.getFecha());
        t.setNotas(request.getNotas());

        return toResponse(transaccionRepository.save(t));
    }

    @Override
    @Transactional
    public TransaccionResponse actualizar(UUID id, TransaccionRequest request) {
        Transaccion t = transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con id " + id));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        t.setCategoria(categoria);
        t.setTipo(request.getTipo());
        t.setConcepto(request.getConcepto());
        t.setMonto(request.getMonto());
        t.setFecha(request.getFecha());
        t.setNotas(request.getNotas());

        return toResponse(transaccionRepository.save(t));
    }

    @Override
    @Transactional
    public TransaccionResponse eliminar(UUID id) {
        Transaccion t = transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con id " + id));
        t.setEliminadoEn(OffsetDateTime.now());
        return toResponse(transaccionRepository.save(t));
    }
}
