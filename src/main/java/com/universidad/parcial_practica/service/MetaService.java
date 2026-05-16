package com.universidad.parcial_practica.service;

import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MetaService {

    @Autowired
    private MetaRepository metaRepository;

    public List<Meta> listarPorUsuario(Usuario usuario) {
        return metaRepository.findByUsuario(usuario);
    }

    public Meta guardar(Meta meta) {
        return metaRepository.save(meta);
    }

    public Meta buscarPorId(Long id) {
        return metaRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        metaRepository.deleteById(id);
    }

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