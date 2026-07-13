package com.finanzasapp.repositories;

import com.finanzasapp.models.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MetaRepository extends JpaRepository<Meta, UUID> {

    @Query("SELECT m FROM Meta m " +
            "WHERE m.usuario.id = :usuarioId AND m.eliminadoEn IS NULL " +
            "ORDER BY m.creadoEn DESC")
    List<Meta> findActivasPorUsuario(@Param("usuarioId") UUID usuarioId);

    @Query("SELECT m FROM Meta m " +
            "WHERE m.usuario.id = :usuarioId AND m.completada = :completada AND m.eliminadoEn IS NULL " +
            "ORDER BY m.creadoEn DESC")
    List<Meta> findActivasPorUsuarioYEstado(@Param("usuarioId") UUID usuarioId, @Param("completada") Boolean completada);

}
