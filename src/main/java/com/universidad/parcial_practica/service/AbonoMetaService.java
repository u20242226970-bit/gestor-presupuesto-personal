package com.universidad.parcial_practica.service;

import com.universidad.parcial_practica.model.AbonoMeta;
import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.repository.AbonoMetaRepository;
import com.universidad.parcial_practica.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AbonoMetaService {

    @Autowired
    private AbonoMetaRepository abonoMetaRepository;

    @Autowired
    private MetaRepository metaRepository;

    public List<AbonoMeta> listarPorMeta(Meta meta) {
        return abonoMetaRepository.findByMeta(meta);
    }

    public AbonoMeta guardar(AbonoMeta abono) {
        // Actualizar el monto ahorrado de la meta
        Meta meta = abono.getMeta();
        double nuevoMonto = (meta.getMontoAhorrado() == null ? 0 : meta.getMontoAhorrado()) + abono.getMonto();
        meta.setMontoAhorrado(nuevoMonto);
        metaRepository.save(meta);
        return abonoMetaRepository.save(abono);
    }

    public void eliminar(Long id) {
        abonoMetaRepository.deleteById(id);
    }
}