package com.finanzasapp.services;

import com.finanzasapp.dto.request.TransaccionRequest;
import com.finanzasapp.dto.response.TransaccionResponse;
import com.finanzasapp.models.Categoria;
import com.finanzasapp.models.Transaccion;
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

    // Mapeo a response
    private TransaccionResponse toResponse(Transaccion t) {
        TransaccionResponse r = new TransaccionResponse();
        r.setId(t.getId());
        r.setTipo(t.getTipo());
        r.setConcepto(t.getConcepto());
        r.setMonto(t.getMonto());
        r.setFecha(t.getFecha());
        r.setNotas(t.getNotas());
        r.setCreadoEn(t.getCreadoEn());
        r.setActualizadoEn(t.getActualizadoEn());

        if (t.getCategoria() != null) {
            r.setCategoriaId(t.getCategoria().getId());
            r.setCategoriaNombre(t.getCategoria().getNombre());
            r.setCategoriaEmoji(t.getCategoria().getEmoji());
            r.setCategoriaColor(t.getCategoria().getColor());
        }

        return r;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransaccionResponse> listarPorUsuario(UUID usuarioId) {
        return transaccionRepository.findActivasPorUsuario(usuarioId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransaccionResponse> listarPorUsuarioYTipo(UUID usuarioId, String tipo) {
        return transaccionRepository.findActivasPorUsuarioYTipo(usuarioId, tipo)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransaccionResponse> listarPorRangoFechas(UUID usuarioId, LocalDate desde, LocalDate hasta) {
        return transaccionRepository.findByUsuarioYRangoFechas(usuarioId, desde, hasta)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TransaccionResponse obtenerPorId(UUID id) {
        return toResponse(
                transaccionRepository.findByIdConCategoria(id)
                        .orElseThrow(() -> new RuntimeException("Transacción no encontrada: " + id))
        );
    }

    @Override
    @Transactional
    public TransaccionResponse crear(TransaccionRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + request.getCategoriaId()));

        Transaccion t = new Transaccion();
        t.setUsuario(usuarioRepository.getReferenceById(request.getUsuarioId())); // solo FK, sin SELECT
        t.setCategoria(categoria); // ya en memoria
        t.setTipo(request.getTipo());
        t.setConcepto(request.getConcepto());
        t.setMonto(request.getMonto());
        t.setFecha(request.getFecha());
        t.setNotas(request.getNotas());

        transaccionRepository.save(t);
        return toResponse(t); // categoria ya cargada, sin SELECT extra
    }

    @Override
    @Transactional
    public TransaccionResponse actualizar(UUID id, TransaccionRequest request) {
        Transaccion t = transaccionRepository.findByIdConCategoria(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada: " + id));

        if (!t.getCategoria().getId().equals(request.getCategoriaId())) {
            Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + request.getCategoriaId()));
            t.setCategoria(categoria);
        }

        t.setTipo(request.getTipo());
        t.setConcepto(request.getConcepto());
        t.setMonto(request.getMonto());
        t.setFecha(request.getFecha());
        t.setNotas(request.getNotas());

        transaccionRepository.save(t);
        return toResponse(t);
    }

    @Override
    @Transactional
    public TransaccionResponse eliminar(UUID id) {
        Transaccion t = transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada: " + id));

        t.setEliminadoEn(OffsetDateTime.now());
        transaccionRepository.save(t);

        TransaccionResponse r = new TransaccionResponse();
        r.setId(t.getId());
        r.setEliminadoEn(t.getEliminadoEn());
        return r;
    }
}