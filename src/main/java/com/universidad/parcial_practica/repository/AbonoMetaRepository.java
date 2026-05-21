package com.universidad.parcial_practica.repository;

import com.universidad.parcial_practica.model.AbonoMeta;
import com.universidad.parcial_practica.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad AbonoMeta.
 * Hereda las operaciones CRUD de JpaRepository y define consultas personalizadas.
 */
@Repository
public interface AbonoMetaRepository extends JpaRepository<AbonoMeta, Long> {

    /**
     * Busca todos los abonos asociados a una meta específica.
     *
     * @param meta la meta de la cual se quieren obtener los abonos
     * @return lista de abonos pertenecientes a la meta
     */
    List<AbonoMeta> findByMeta(Meta meta);
}