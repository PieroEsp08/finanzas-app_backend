package com.finanzasapp.repositories;

import com.finanzasapp.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

    @Query("SELECT c FROM Categoria c " +
            "WHERE c.eliminadoEn IS NULL " +
            "AND (c.usuario.id = :usuarioId OR c.esDefault = true) " +
            "ORDER BY c.esDefault DESC, c.nombre ASC")
    List<Categoria> findActivasPorUsuario(@Param("usuarioId") UUID usuarioId);

    @Query("SELECT c FROM Categoria c " +
            "WHERE c.eliminadoEn IS NULL AND c.tipo = :tipo " +
            "AND (c.usuario.id = :usuarioId OR c.esDefault = true) " +
            "ORDER BY c.esDefault DESC, c.nombre ASC")
    List<Categoria> findActivasPorUsuarioYTipo(@Param("usuarioId") UUID usuarioId, @Param("tipo") String tipo);
}