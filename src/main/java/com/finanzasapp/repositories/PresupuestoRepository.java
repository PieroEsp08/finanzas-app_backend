package com.finanzasapp.repositories;

import com.finanzasapp.models.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, UUID> {

    @Query("SELECT p FROM Presupuesto p " +
            "WHERE p.usuario.id = :usuarioId AND p.eliminadoEn IS NULL " +
            "ORDER BY p.creadoEn DESC")
    List<Presupuesto> findActivosPorUsuario(@Param("usuarioId") UUID usuarioId);

    @Query("SELECT p FROM Presupuesto p " +
            "WHERE p.usuario.id = :usuarioId AND p.mes = :mes AND p.anio = :anio AND p.eliminadoEn IS NULL " +
            "ORDER BY p.creadoEn DESC")
    List<Presupuesto> findActivosPorUsuarioYPeriodo(
            @Param("usuarioId") UUID usuarioId,
            @Param("mes") Short mes,
            @Param("anio") Short anio);
}
