package com.finanzasapp.services;

import com.finanzasapp.dto.request.AbonoMetaRequest;
import com.finanzasapp.dto.response.AbonoMetaResponse;
import com.finanzasapp.models.AbonoMeta;
import com.finanzasapp.models.Meta;
import com.finanzasapp.models.Usuario;
import com.finanzasapp.repositories.AbonoMetaRepository;
import com.finanzasapp.repositories.MetaRepository;
import com.finanzasapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AbonoMetaServiceImpl implements AbonoMetaService{

    @Autowired
    private AbonoMetaRepository abonoMetaRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private AbonoMetaResponse toResponse(AbonoMeta a) {
        AbonoMetaResponse response = new AbonoMetaResponse();
        response.setId(a.getId());
        response.setMetaId(a.getMeta().getId());
        response.setMonto(a.getMonto());
        response.setNotas(a.getNotas());
        response.setCreadoEn(a.getCreadoEn());
        response.setActualizadoEn(a.getActualizadoEn());
        return response;
    }

    @Override
    @Transactional
    public List<AbonoMetaResponse> listarPorMeta(UUID metaId) {
        return abonoMetaRepository.findActivosPorMeta(metaId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AbonoMetaResponse crear(AbonoMetaRequest request) {
        Meta meta = metaRepository.findById(request.getMetaId())
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        AbonoMeta abono = new AbonoMeta();
        abono.setMeta(meta);
        abono.setUsuario(usuario);
        abono.setMonto(request.getMonto());
        abono.setNotas(request.getNotas());

        AbonoMeta guardado = abonoMetaRepository.save(abono);

        // Actualizar montoActual de la meta
        meta.setMontoActual(meta.getMontoActual().add(request.getMonto()));
        meta.setCompletada(meta.getMontoActual().compareTo(meta.getMontoObjetivo()) >= 0);
        metaRepository.save(meta);

        return toResponse(guardado);
    }

    @Override
    @Transactional
    public AbonoMetaResponse actualizar(UUID id, AbonoMetaRequest request) {
        AbonoMeta abono = abonoMetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abono no encontrado con id " + id));

        BigDecimal montoAnterior = abono.getMonto();
        BigDecimal montoNuevo = request.getMonto();

        Meta meta = abono.getMeta();
        BigDecimal diferencia = montoNuevo.subtract(montoAnterior);
        meta.setMontoActual(meta.getMontoActual().add(diferencia));
        meta.setCompletada(meta.getMontoActual().compareTo(meta.getMontoObjetivo()) >= 0);
        metaRepository.save(meta);

        abono.setMonto(montoNuevo);
        abono.setNotas(request.getNotas());

        return toResponse(abonoMetaRepository.save(abono));
    }

    @Override
    @Transactional
    public AbonoMetaResponse eliminar(UUID id) {
        AbonoMeta abono = abonoMetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abono no encontrado con id " + id));

        Meta meta = abono.getMeta();
        meta.setMontoActual(meta.getMontoActual().subtract(abono.getMonto()));
        meta.setCompletada(meta.getMontoActual().compareTo(meta.getMontoObjetivo()) >= 0);
        metaRepository.save(meta);

        abono.setEliminadoEn(OffsetDateTime.now());
        return toResponse(abonoMetaRepository.save(abono));
    }

}
