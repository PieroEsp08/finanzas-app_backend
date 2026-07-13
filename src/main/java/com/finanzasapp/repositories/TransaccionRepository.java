package com.finanzasapp.repositories;

import com.finanzasapp.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransaccionRepository extends JpaRepository<Transaccion, UUID> {

    @Query("SELECT t FROM Transaccion t " +
            "WHERE t.usuario.id = :usuarioId AND t.eliminadoEn IS NULL " +
            "ORDER BY t.fecha DESC")
    List<Transaccion> findActivasPorUsuario(@Param("usuarioId") UUID usuarioId);

    @Query("SELECT t FROM Transaccion t " +
            "WHERE t.usuario.id = :usuarioId AND t.tipo = :tipo AND t.eliminadoEn IS NULL " +
            "ORDER BY t.fecha DESC")
    List<Transaccion> findActivasPorUsuarioYTipo(@Param("usuarioId") UUID usuarioId, @Param("tipo") String tipo);

    @Query("SELECT t FROM Transaccion t " +
            "WHERE t.usuario.id = :usuarioId AND t.eliminadoEn IS NULL " +
            "AND t.fecha BETWEEN :desde AND :hasta " +
            "ORDER BY t.fecha DESC")
    List<Transaccion> findByUsuarioYRangoFechas(
            @Param("usuarioId") UUID usuarioId,
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta);

}
