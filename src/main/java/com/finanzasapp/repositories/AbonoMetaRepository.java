package com.finanzasapp.repositories;

import com.finanzasapp.models.AbonoMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AbonoMetaRepository extends JpaRepository<AbonoMeta, UUID> {

    @Query("SELECT a FROM AbonoMeta a " +
            "WHERE a.meta.id = :metaId AND a.eliminadoEn IS NULL " +
            "ORDER BY a.creadoEn DESC")
    List<AbonoMeta> findActivosPorMeta(@Param("metaId") UUID metaId);


}
