package com.universidad.parcial_practica.repository;

import com.universidad.parcial_practica.model.AbonoMeta;
import com.universidad.parcial_practica.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AbonoMetaRepository extends JpaRepository<AbonoMeta, Long> {
    List<AbonoMeta> findByMeta(Meta meta);
}