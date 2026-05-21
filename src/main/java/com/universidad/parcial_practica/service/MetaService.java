package com.universidad.parcial_practica.service;

import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que contiene la lógica de negocio para las metas de ahorro.
 */
@Service
public class MetaService {

    @Autowired
    private MetaRepository metaRepository;

    /**
     * Lista todas las metas de un usuario específico.
     *
     * @param usuario el usuario del cual se quieren las metas
     * @return lista de metas del usuario
     */
    public List<Meta> listarPorUsuario(Usuario usuario) {
        return metaRepository.findByUsuario(usuario);
    }

    /**
     * Guarda una nueva meta.
     *
     * @param meta datos de la meta a guardar
     * @return la meta guardada
     */
    public Meta guardar(Meta meta) {
        return metaRepository.save(meta);
    }

    /**
     * Busca una meta por su identificador.
     *
     * @param id identificador de la meta
     * @return la meta encontrada, o null si no existe
     */
    public Meta buscarPorId(Long id) {
        return metaRepository.findById(id).orElse(null);
    }

    /**
     * Elimina una meta por su identificador.
     *
     * @param id identificador de la meta a eliminar
     */
    public void eliminar(Long id) {
        metaRepository.deleteById(id);
    }

    /**
     * Actualiza una meta existente con nuevos datos.
     *
     * @param id identificador de la meta a actualizar
     * @param nueva nuevos datos de la meta
     * @return la meta actualizada, o null si no existe
     */
    public Meta actualizar(Long id, Meta nueva) {
        Meta existente = metaRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(nueva.getNombre());
            existente.setEmoji(nueva.getEmoji());
            existente.setPrecioObjetivo(nueva.getPrecioObjetivo());
            existente.setFechaLimite(nueva.getFechaLimite());
            return metaRepository.save(existente);
        }
        return null;
    }
}